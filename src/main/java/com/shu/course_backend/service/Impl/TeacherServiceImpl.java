package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.ConstDoMapper;
import com.shu.course_backend.dao.CourseDoMapper;
import com.shu.course_backend.dao.ElectionDoMapper;
import com.shu.course_backend.dao.OpenDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Grade;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.CourseDo;
import com.shu.course_backend.model.entity.ElectionDo;
import com.shu.course_backend.model.entity.ElectionDoExample;
import com.shu.course_backend.model.entity.OpenDo;
import com.shu.course_backend.model.response.GradeResponse;
import com.shu.course_backend.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

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
}
