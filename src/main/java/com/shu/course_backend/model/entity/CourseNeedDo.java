package com.shu.course_backend.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
* @program: CourseNeedDo
* @Description: 获取课程列表请求体
* @Author: SoCMo
* @Date: 2021/5/18
*/
@Data
@NoArgsConstructor
public class CourseNeedDo {
    private Integer courseId;

    private String courseName;

    private Integer credit;

    private List<String> courseTime;

    private String teacherId;

    private String teacherName;

    private Boolean notFull;

    private String semester;
}
