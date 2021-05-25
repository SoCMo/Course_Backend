package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.CourseNeedDo;

/**
 * @program: CourseService
 * @Description: 课程接口
 * @Author: SoCMo
 * @Date: 2021/5/18
 */
public interface CourseService {
    Result getCourseList(CourseNeedDo courseNeedDo);
}
