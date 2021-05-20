package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.*;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.*;
import com.shu.course_backend.model.response.CourseResponse;
import com.shu.course_backend.model.response.Info.CourseFilterRes;
import com.shu.course_backend.service.CourseService;
import com.shu.course_backend.tool.AuthTool;
import com.shu.course_backend.tool.CourseTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final ConstDoMapper constDoMapper;

    private final OpenDoMapper openDoMapper;

    private final CourseDoMapper courseDoMapper;

    private final UserDoMapper userDoMapper;

    private final CourseTimeDoMapper courseTimeDoMapper;

    private final ElectionDoMapper electionDoMapper;

    private final AuthTool authTool;

    @Override
    public Result getCourseList(CourseNeedDo courseNeedDo) {
        try{
            OpenDoExample openDoExample = new OpenDoExample();
            OpenDoExample.Criteria openCriteria = openDoExample.createCriteria();

            //获取开课信息
            if(StringUtils.isBlank(courseNeedDo.getSemester())){
                ConstDo constDo = null;
                if((constDo = constDoMapper.selectByPrimaryKey("NOW_SEMESTER")) == null){
                    throw new AllException(EmAllException.DATABASE_ERROR);
                }
                openCriteria.andSemesterEqualTo(constDo.getConfigValue());
            }else {
                openCriteria.andSemesterEqualTo(courseNeedDo.getSemester());
            }

            if(!StringUtils.isBlank(courseNeedDo.getTeacherId())){
                openCriteria.andTeacherIdEqualTo(courseNeedDo.getTeacherId());
            }

            List<OpenDo> openDoList = openDoMapper.selectByExample(openDoExample);
            if(openDoList == null){
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
            if(openDoList.isEmpty()){
                return Result.error(EmAllException.EMPTY_RESPONSE, "筛选课程为空");
            }

            //获取课程信息
            List<Integer> courseIdList = openDoList.stream()
                    .map(OpenDo::getCourseId).collect(Collectors.toList());
            CourseDoExample courseDoExample = new CourseDoExample();
            CourseDoExample.Criteria courseCriteria = courseDoExample.createCriteria();
            courseCriteria.andIdIn(courseIdList);
            if(courseNeedDo.getCredit() != null){
                courseCriteria.andCreditEqualTo(courseNeedDo.getCredit());
            }
            if(!StringUtils.isBlank(courseNeedDo.getCourseName())){
                courseCriteria.andNameLike("%" + courseNeedDo.getCourseName() + "%");
            }
            if(courseNeedDo.getCourseId() != null){
                courseCriteria.andIdEqualTo(courseNeedDo.getCourseId());
            }

            List<CourseDo> courseDoList = courseDoMapper.selectByExample(courseDoExample);
            if(courseDoList == null){
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
            if(courseDoList.isEmpty()){
                return Result.error(EmAllException.EMPTY_RESPONSE, "筛选课程为空");
            }
            Map<Integer, CourseDo> courseDoMap
                    = courseDoList.stream().collect(Collectors.toMap(CourseDo::getId, courseDo -> courseDo));

            //过滤开课列表
            openDoList = openDoList.stream().filter(openDo -> courseDoMap.get(openDo.getCourseId()) != null)
                    .collect(Collectors.toList());

            //获取教师名等信息
            List<String> teacherIdList = openDoList.stream()
                    .map(OpenDo::getTeacherId).collect(Collectors.toList());
            UserDoExample userDoExample = new UserDoExample();
            UserDoExample.Criteria userCriteria = userDoExample.createCriteria();
            userCriteria.andUserIdIn(teacherIdList);
            if(!StringUtils.isBlank(courseNeedDo.getTeacherName())){
                userCriteria.andNameLike("%" + courseNeedDo.getTeacherName() + "%");
            }
            List<UserDo> teacherDoList = userDoMapper.selectByExample(userDoExample);
            if(teacherDoList == null){
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
            if(teacherDoList.isEmpty()){
                return Result.error(EmAllException.EMPTY_RESPONSE, "筛选课程为空");
            }
            Map<String, UserDo> teacherDoMap
                    = teacherDoList.stream().collect(Collectors.toMap(UserDo::getUserId, userDo -> userDo));

            //过滤开课列表
            openDoList = openDoList.stream().filter(openDo -> teacherDoMap.get(openDo.getTeacherId()) != null)
                    .collect(Collectors.toList());

            //获取课程时间等信息
            List<Integer> courseTimeIdList = openDoList.stream()
                    .map(OpenDo::getCourseTimeId).collect(Collectors.toList());
            CourseTimeDoExample courseTimeDoExample = new CourseTimeDoExample();
            courseTimeDoExample.createCriteria()
                    .andIdIn(courseTimeIdList);
            List<CourseTimeDo> courseTimeDoList = courseTimeDoMapper.selectByExample(courseTimeDoExample);
            if(courseTimeDoList == null){
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
            if(courseTimeDoList.isEmpty()){
                return Result.error(EmAllException.EMPTY_RESPONSE, "筛选课程为空");
            }
            Map<Integer, CourseTimeDo> courseTimeDoMap
                    = courseTimeDoList.stream().collect(Collectors.toMap(CourseTimeDo::getId, courseTimeDo -> courseTimeDo));

            List<String> needTime = null;
            if(courseNeedDo.getCourseTime() != null){
                needTime = CourseTool.translateFromStrToBitList(courseNeedDo.getCourseTime());
            }


            List<String> finalNeedTime = needTime;
            List<CourseFilterRes> courseFilterResList = openDoList.stream()
                    .map(openDo -> {
                        CourseFilterRes courseFilterRes = new CourseFilterRes();
                        if(finalNeedTime != null){
                            if(CourseTool.conflictCheck(finalNeedTime,
                                    courseTimeDoMap.get(openDo.getCourseTimeId()).getCourseTime()))
                                return null;
                        }
                        BeanUtils.copyProperties(openDo, courseFilterRes);

                        CourseDo courseDo = courseDoMap.get(openDo.getCourseId());
                        BeanUtils.copyProperties(courseDo, courseFilterRes);
                        courseFilterRes.setCourseId(courseDo.getId());
                        courseFilterRes.setCourseName(courseDo.getName());

                        UserDo teacherDo = teacherDoMap.get(openDo.getTeacherId());
                        courseFilterRes.setTeacherId(teacherDo.getUserId());
                        courseFilterRes.setTeacherName(teacherDo.getName());

                        ElectionDoExample electionDoExample = new ElectionDoExample();
                        // TODO: 修改
//                        electionDoExample.createCriteria()
//                                .andCourseIdEqualTo(openDo.getCourseId());
                        List<ElectionDo> electionDoList = electionDoMapper.selectByExample(electionDoExample);
                        if(electionDoList == null) return null;
                        courseFilterRes.setChosenNum(electionDoList.size());
                        if(courseNeedDo.getNotFull() != null &&
                                courseFilterRes.getCapacity() < courseFilterRes.getChosenNum()){
                            return null;
                        }
                        courseFilterRes.setIsChosen(electionDoList.stream()
                                .anyMatch(electionDo -> electionDo.getStudentId()
                                        .equals(authTool.getUserId())));

                        courseFilterRes.setCourseTimeList(
                                CourseTool.translateFromBitToStr(
                                        courseTimeDoMap.get(openDo.getCourseTimeId()).getCourseTime()
                                )
                        );
                        return courseFilterRes;
                    }).collect(Collectors.toList());

            courseFilterResList = courseFilterResList.stream()
                    .filter(Objects::nonNull).collect(Collectors.toList());
            if(courseFilterResList.isEmpty()){
                return Result.error(EmAllException.EMPTY_RESPONSE, "筛选课程为空");
            }
            return Result.success(courseFilterResList);
        } catch (AllException ex){
            log.error(ex.getMsg());
            return Result.error(ex);
        }


    }
}
