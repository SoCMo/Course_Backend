package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.*;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.*;
import com.shu.course_backend.model.response.Info.SelectionInfoRes;
import com.shu.course_backend.service.StudentService;
import com.shu.course_backend.tool.AuthTool;
import com.shu.course_backend.tool.CourseTool;
import com.shu.course_backend.tool.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * program: StudentServiceImpl
 * description: 学生资源实现层
 * author: SoCMo
 * create: 2021/5/15 11:50
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final OpenDoMapper openDoMapper;

    private final ElectionDoMapper electionDoMapper;

    private final ConstDoMapper constDoMapper;

    private final UserDoMapper userDoMapper;

    private final CourseTimeDoMapper courseTimeDoMapper;

    private final CourseDoMapper courseDoMapper;

    private final AuthTool authTool;

    @Override
    public Result selectCourse(Integer openId) {
        try{
            ConstDo constDo = constDoMapper.selectByPrimaryKey("NOW_ELECTIONSTATE");
            if(constDo == null) throw new AllException(EmAllException.DATABASE_ERROR);
            if(!Boolean.parseBoolean(constDo.getConfigValue())){
                return Result.error(EmAllException.BAD_REQUEST, "当前还未开始选课!");
            }

            OpenDo openDo = null;
            if((openDo = openDoMapper.selectByPrimaryKey(openId)) == null){
                return Result.error(EmAllException.BAD_REQUEST, "不存在这门开课课程");
            }

            ElectionDoExample electionDoExample = new ElectionDoExample();
            electionDoExample.createCriteria()
                    .andStudentIdEqualTo(authTool.getUserId());
            List<ElectionDo> electionDoList = electionDoMapper.selectByExample(electionDoExample);
            if(electionDoList.stream().anyMatch(electionDo -> electionDo.getCourseId().equals(openId))){
                return Result.error(EmAllException.BAD_REQUEST, "已选择该门课程!");
            }

            if(!electionDoList.isEmpty()){
                List<Integer> openIdList = electionDoList.stream()
                        .map(ElectionDo::getCourseId)
                        .collect(Collectors.toList());

                constDo = constDoMapper.selectByPrimaryKey("NOW_SEMESTER");
                if(constDo == null) throw new AllException(EmAllException.DATABASE_ERROR);
                String semester = constDo.getConfigValue();

                OpenDoExample openDoExample = new OpenDoExample();
                openDoExample.createCriteria()
                        .andOpenIdIn(openIdList)
                        .andSemesterEqualTo(semester);
                List<OpenDo> openDoList = openDoMapper.selectByExample(openDoExample);

                CourseTimeDoExample courseTimeDoExample = new CourseTimeDoExample();
                courseTimeDoExample.createCriteria()
                        .andIdIn(openDoList.stream().map(OpenDo::getCourseTimeId).collect(Collectors.toList()));
                List<CourseTimeDo> courseTimeDoList = courseTimeDoMapper.selectByExample(courseTimeDoExample);
                List<String> consumeTimeList = courseTimeDoList.stream()
                                .map(CourseTimeDo::getCourseTime)
                                .collect(Collectors.toList());

                CourseTimeDo courseTimeDo = courseTimeDoMapper.selectByPrimaryKey(openDo.getCourseTimeId());

                if(CourseTool.conflictCheck(consumeTimeList, courseTimeDo.getCourseTime())){
                    return Result.error(EmAllException.BAD_REQUEST, "存在课时冲突!");
                }
            }

            ElectionDo electionDo = new ElectionDo();
            electionDo.setCourseId(openId);
            electionDo.setStudentId(authTool.getUserId());

            if(electionDoMapper.insertSelective(electionDo) >= 1){
                electionDo = electionDoMapper.selectByPrimaryKey(electionDo.getId());
                if(electionDo != null) return Result.success(electionDo);
            }

            return Result.error(EmAllException.DATABASE_ERROR);
        }catch (AllException ex) {
            log.error(ex.getMsg());
            return Result.error(ex);
        }
    }

    @Override
    public Result selectionList(String semester){
        try {
            if(StringUtils.isEmpty(semester)){
                ConstDo constDo = constDoMapper.selectByPrimaryKey("NOW_SEMESTER");
                if(constDo == null) throw new AllException(EmAllException.DATABASE_ERROR);
                semester = constDo.getConfigValue();
            }

            ElectionDoExample electionDoExample = new ElectionDoExample();
            electionDoExample.createCriteria()
                    .andStudentIdEqualTo(authTool.getUserId());
            List<ElectionDo> electionDoList = electionDoMapper.selectByExample(electionDoExample);

            if(electionDoList.isEmpty()){
                return Result.error(EmAllException.EMPTY_RESPONSE, "还未选过课！");
            }

            List<Integer> openIdList = electionDoList.stream()
                    .map(ElectionDo::getCourseId)
                    .collect(Collectors.toList());

            OpenDoExample openDoExample = new OpenDoExample();
            openDoExample.createCriteria()
                    .andOpenIdIn(openIdList)
                    .andSemesterEqualTo(semester);
            List<OpenDo> openDoList = openDoMapper.selectByExample(openDoExample);
            if(openDoList.isEmpty()){
                return Result.error(EmAllException.EMPTY_RESPONSE, "该学期还未选过课！");
            }
            Map<Integer, OpenDo> openDoMapper = openDoList.stream()
                    .collect(Collectors.toMap(OpenDo::getOpenId, openDo -> openDo));

            UserDoExample userDoExample = new UserDoExample();
            userDoExample.createCriteria()
                    .andUserIdIn(openDoList.stream().map(OpenDo::getTeacherId).collect(Collectors.toList()));
            List<UserDo> teacherDoList = userDoMapper.selectByExample(userDoExample);
            if(teacherDoList.size() != openDoList.size()){
                throw new AllException(EmAllException.DATABASE_ERROR, "数据库错误，查询不到教师信息！");
            }
            Map<String, String> teacherDoMapper = teacherDoList.stream()
                    .collect(Collectors.toMap(UserDo::getUserId, UserDo::getName));

            CourseTimeDoExample courseTimeDoExample = new CourseTimeDoExample();
            courseTimeDoExample.createCriteria()
                    .andIdIn(openDoList.stream().map(OpenDo::getCourseTimeId).collect(Collectors.toList()));
            List<CourseTimeDo> courseTimeDoList = courseTimeDoMapper.selectByExample(courseTimeDoExample);
            Map<Integer, String> courseTimeDoMapper = courseTimeDoList.stream()
                    .collect(Collectors.toMap(CourseTimeDo::getId, CourseTimeDo::getCourseTime));

            CourseDoExample courseDoExample = new CourseDoExample();
            courseDoExample.createCriteria()
                    .andIdIn(openDoList.stream().map(OpenDo::getCourseId).collect(Collectors.toList()));
            List<CourseDo> courseDoList = courseDoMapper.selectByExample(courseDoExample);
            Map<Integer, CourseDo> courseDoMapper = courseDoList.stream()
                    .collect(Collectors.toMap(CourseDo::getId, courseDo -> courseDo));

            List<SelectionInfoRes> selectionInfoResList =
                    electionDoList.stream().map(electionDo -> {
                        SelectionInfoRes selectionInfoRes = new SelectionInfoRes();

                        OpenDo openDo = openDoMapper.get(electionDo.getCourseId());
                        CourseDo courseDo = courseDoMapper.get(openDo.getCourseId());

                        BeanUtils.copyProperties(electionDo, selectionInfoRes);
                        selectionInfoRes.setSemester(StrUtil.semesterConversion(openDo.getSemester()));
                        selectionInfoRes.setTeacherName(teacherDoMapper.get(openDo.getTeacherId()));
                        selectionInfoRes.setCourseTime(
                                CourseTool.translateFromBitToStr(
                                        courseTimeDoMapper.get(openDo.getCourseTimeId())
                                )
                        );
                        selectionInfoRes.setCourseName(courseDo.getName());
                        selectionInfoRes.setProportion(courseDo.getProportion() + "%");

                        return selectionInfoRes;
                    }).collect(Collectors.toList());

            return Result.success(selectionInfoResList);
        } catch (AllException ex){
            log.error(ex.getMsg());
            return Result.error(ex);
        }

    }


}
