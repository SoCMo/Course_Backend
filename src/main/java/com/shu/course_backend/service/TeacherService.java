package com.shu.course_backend.service;

import com.shu.course_backend.model.Grade;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.request.GradeRequest;

import java.util.List;

/**
 * @ClassName: TeacherService
 * @Description:
 * @Author: pongshy
 * @Date: 2021/5/5 22:06
 **/
public interface TeacherService {


    /*
     * @Description: 申请课程
     * @Method: [courseId, userId]
     * @Return: com.shu.course_backend.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/5 22:14
     */
    Result applyTheCourseByTeacher(Integer courseId, Integer courseTimeId, String userId);

    /*
     * @Description: 获取指定课程的所有学生成绩
     * @Method: [courseId]
     * @Return: com.shu.course_backend.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/15 22:04
     */
    Result getAllGrades(Integer openId);

    /*
     * @Description: 输入学生成绩
     * @Method: [courseId, gradeList]
     * @Return: com.shu.course_backend.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/16 22:01
     */
    Result enterStudentGrades(Integer courseId, List<Grade> gradeList);

    /*
     * @Description: 获取现在能选的所有课程
     * @Param: [teacherid]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/18
     * @Version: V1.0
     **/
    Result getCourses(String teacherid);

    /*
     * @Description: 获取指定学期教师的开课情况
     * @Param: [semester, teacherid]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/18
     * @Version: V1.0
     **/
    Result getSemesterCourses(String semester, String teacherid);

}
