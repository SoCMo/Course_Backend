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
    Result applyTheCourseByTeacher(Integer courseId, String userId);


}
