package com.shu.course_backend.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: CourseRequest
 * @Description: 课程信息
 * @Author: pongshy
 * @Date: 2021/4/25-19:30
 * @Version: V1.0
 **/
@Data
public class CourseRequest {


    @NotEmpty
    private String courseName;

    @NotNull
    private Integer credit;

    @NotNull
    private Integer capacity;

    @NotNull
    private Integer proportion;
}
