package com.shu.course_backend.model.response.Info;

import lombok.Data;

import java.util.List;

/**
 * program: CourseInfoRes
 * description: 选课信息返回
 * author: SoCMo
 * create: 2021/5/15 12:12
 */
@Data
public class SelectionInfoRes {
    private Integer id;

    private String courseName;

    private String teacherName;

    private String semester;

    private List<String> courseTime;

    private Double grade;

    private Integer usual;

    private Integer examination;

    private Double point;

    private String proportion;
}
