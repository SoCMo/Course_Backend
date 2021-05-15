package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;

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
    Result getAllGrades(Integer courseId);

}
