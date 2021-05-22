package com.shu.course_backend.model.response.Info;

import com.shu.course_backend.model.entity.CourseDo;
import com.shu.course_backend.model.response.CourseTimeResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * program: CourseInfoRes
 * description: 选课信息返回
 * author: SoCMo
 * create: 2021/5/15 12:12
 */
@Data
@NoArgsConstructor
public class SelectionInfoRes {
    private Integer id;

    private String courseName;

    private String teacherName;

    private String semester;

    private Double grade;

    private Integer usual;

    private Integer examination;

    private CourseTimeResponse courseTimeResponse;

    private Integer credit;

    private Integer capacity;

    private Double point;

    private String proportion;
}
