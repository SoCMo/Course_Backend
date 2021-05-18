package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.*;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Grade;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.*;
import com.shu.course_backend.model.response.CourseResponse;
import com.shu.course_backend.model.response.CourseTimeResponse;
import com.shu.course_backend.model.response.GradeResponse;
import com.shu.course_backend.model.response.SemCourseResponse;
import com.shu.course_backend.service.TeacherService;
import com.shu.course_backend.tool.CourseTool;
import com.shu.course_backend.tool.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName: TeacherServiceImpl
 * @Description:
 * @Author: pongshy
 * @Date: 2021/5/5 22:06
 **/
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {



    @Resource
    private ConstDoMapper constDoMapper;

    @Resource
    private OpenDoMapper openDoMapper;

    @Resource
    private CourseDoMapper courseDoMapper;

    @Resource
    private ElectionDoMapper electionDoMapper;

    @Resource
    private CourseTimeDoMapper courseTimeDoMapper;

    /*
     * @Description: 申请课程
     * @Method: [courseId, userId]
     * @Return: com.shu.course_backend.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/5 22:14
     */
    @Override
    public Result applyTheCourseByTeacher(Integer courseId, Integer courseTimeId, String userId) {
        OpenDo record = new OpenDo();
        record.setCourseId(courseId);
        record.setCourseTimeId(courseTimeId);
        record.setTeacherId(userId);

        try {
            if (openDoMapper.insertSelective(record) == 1) {
                return Result.success("申请成功");
            } else {
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
        } catch (AllException e) {
            return Result.error(e);
        }
    }

    /*
     * @Description: 获取指定课程的所有学生成绩
     * @Method: [courseId]
     * @Return: com.shu.course_backend.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/15 22:04
     */
    @Override
    public Result getAllGrades(Integer courseId) {
        try {
            CourseDo courseDo = courseDoMapper.selectByPrimaryKey(courseId);

            if (ObjectUtils.isEmpty(courseDo)) {
                throw new AllException(EmAllException.NOT_FOUND);
            }
            List<GradeResponse> responseList = electionDoMapper.selectNameAndGrade(courseId);

            return Result.success(responseList);
        } catch (AllException e) {
            return Result.error(e);
        }

    }

    /*
     * @Description: 输入学生成绩
     * @Method: [courseId, gradeList]
     * @Return: com.shu.course_backend.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/16 22:01
     */
    @Override
    public Result enterStudentGrades(Integer courseId, List<Grade> gradeList) {
        try {
            CourseDo courseDo = courseDoMapper.selectByPrimaryKey(courseId);
            if (ObjectUtils.isEmpty(courseDo)) {
                throw new AllException(EmAllException.NOT_FOUND);
            }
            // 平时成绩占比;
            Integer proportion = courseDo.getProportion();
            Double pro = (double) proportion / 100.0;

            for (Grade grade : gradeList) {
                String studentId = grade.getStudentId();
                Double gd = (double) grade.getUsual() * pro
                        + (double) grade.getExam() * (1 - pro);
                ElectionDoExample example = new ElectionDoExample();

                example
                        .createCriteria()
                        .andCourseIdEqualTo(courseId)
                        .andStudentIdEqualTo(studentId);
                ElectionDo record = new ElectionDo();
                record.setUsual(grade.getUsual());
                record.setExamination(grade.getExam());
                record.setGrade(gd);
                try {
                    electionDoMapper.updateByExampleSelective(record, example);
                } catch (Exception e) {
                    log.info("studentId: " + studentId + " 成绩插入失败");
                }
            }
            return Result.success();
        } catch (AllException e) {
            return Result.error(e);
        }
    }

    /*
     * @Description: 获取现在能选的所有课程
     * @Param: [teacherid]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/18
     * @Version: V1.0
     **/
    @Override
    public Result getCourses(String teacherid) {
        List<CourseDo> courseDoList = courseDoMapper.selectCourseWhichHaveTime();
        List<CourseResponse> responses = new ArrayList<>();
        String semester = constDoMapper.selectByPrimaryKey("NOW_SEMESTER")
                .getConfigValue();

        for (CourseDo courseDo : courseDoList) {
            CourseResponse tmp = new CourseResponse();

            BeanUtils.copyProperties(courseDo, tmp);

            CourseTimeDoExample example = new CourseTimeDoExample();
            example
                    .createCriteria()
                    .andCourseIdEqualTo(courseDo.getId());
            List<CourseTimeDo> timeDoList = courseTimeDoMapper.selectByExample(example);
            List<CourseTimeResponse> timeResponses = new ArrayList<>();
            // 是否已被选择
            OpenDoExample openDoExample1 = new OpenDoExample();
            openDoExample1
                    .createCriteria()
                    .andCourseIdEqualTo(courseDo.getId())
                    .andTeacherIdEqualTo(teacherid)
                    .andSemesterEqualTo(semester);
            Integer ct = openDoMapper.countByExample(openDoExample1);
            if (ct == 0) {
                tmp.setIsChosen(0);
            } else {
                tmp.setIsChosen(1);
            }

            // 上课时间
            for (CourseTimeDo courseTimeDo : timeDoList) {
                CourseTimeResponse resTmp = new CourseTimeResponse();
                BeanUtils.copyProperties(courseTimeDo, resTmp);
                resTmp.setCourseTimeList(CourseTool.translateFromBitToStr(courseTimeDo.getCourseTime()));
                //查看该时间段老师是否已开课
                Integer id = courseTimeDo.getId();
                OpenDoExample openDoExample = new OpenDoExample();
                openDoExample
                        .createCriteria()
                        .andCourseTimeIdEqualTo(id)
                        .andSemesterEqualTo(semester);
                Integer count = openDoMapper.countByExample(openDoExample);
                if (count == 0) {
                    resTmp.setIsChosen(0);
                } else {
                    resTmp.setIsChosen(1);
                }

                timeResponses.add(resTmp);
            }
            tmp.setTimeList(timeResponses);
            responses.add(tmp);
        }

        return Result.success(responses);
    }

    /*
     * @Description: 获取指定学期教师的开课情况
     * @Param: [semester, teacherid]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/18
     * @Version: V1.0
     **/
    @Override
    public Result getSemesterCourses(String semester, String teacherid) {
        List<SemCourseResponse> responses = new ArrayList<>();
        // 处理学期
        semester = StrUtil.semesterFromStrToInt(semester);

        OpenDoExample openDoExample = new OpenDoExample();
        openDoExample
                .createCriteria()
                .andSemesterEqualTo(semester)
                .andTeacherIdEqualTo(teacherid);
        List<OpenDo> openDoList = openDoMapper.selectByExample(openDoExample);

        for (OpenDo openDo : openDoList) {
            StringBuffer sb = new StringBuffer();
            SemCourseResponse tmp_res = new SemCourseResponse();
            Integer courseid = openDo.getCourseId();
            Integer course_time_id = openDo.getCourseTimeId();

            tmp_res.setOpenid(openDo.getOpenId());
            CourseDo courseDo = courseDoMapper.selectByPrimaryKey(courseid);
            BeanUtils.copyProperties(courseDo, tmp_res);
            tmp_res.setCourseId(courseid);

            CourseTimeDo courseTimeDo = courseTimeDoMapper.selectByPrimaryKey(course_time_id);
            BeanUtils.copyProperties(courseTimeDo, tmp_res);
            tmp_res.setCourseTimeList(CourseTool.translateFromBitToStr(courseTimeDo.getCourseTime()));

            // 查看选课人数
            ElectionDoExample electionDoExample = new ElectionDoExample();
            electionDoExample
                    .createCriteria()
                    .andCourseIdEqualTo(courseid);
            Integer count = electionDoMapper.countByExample(electionDoExample);
            sb.append(count.toString());
            sb.append("/");
            sb.append(courseDo.getProportion().toString());
            tmp_res.setChoseAndAll(sb.toString());

            responses.add(tmp_res);
        }

        return Result.success(responses);
    }
}
