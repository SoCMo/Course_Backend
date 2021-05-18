package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;

/**
 * program: StudentService
 * description: 学生资源
 * author: SoCMo
 * create: 2021/5/15 11:48
 */
public interface StudentService {
    Result selectCourse(Integer openId);

    Result selectionList(String semester);

    Result quiteCourse(Integer openId);
}
