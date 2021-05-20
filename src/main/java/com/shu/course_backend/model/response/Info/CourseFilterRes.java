package com.shu.course_backend.model.response.Info;

import lombok.Data;

import java.util.List;

/**
 * @program: CourseFilterRes
 * @Description: 多条件课程信息返回
 * @Author: SoCMo
 * @Date: 2021/5/18
 */
@Data

public class CourseFilterRes {
    private Integer courseId;

    private Integer openId;

    private Integer courseTimeId;

    private String teacherId;

    private String teacherName;

    private String courseName;

    private Integer credit;

    private Integer chosenNum;

    private Integer capacity;

    private Integer proportion;

    private Boolean isChosen;

    private List<String> courseTimeList;
}
