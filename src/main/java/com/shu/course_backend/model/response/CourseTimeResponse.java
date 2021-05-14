package com.shu.course_backend.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CourseTimeResponse
 * @Description: 课程时间等详细返回类
 * @Author: pongshy
 * @Date: 2021/5/14-16:03
 * @Version: V1.0
 **/
@Data
public class CourseTimeResponse {


    private Integer id;

    private String address;

    private List<String> courseTimeList;

    private String answerAddress;

    private String answerTime;

    private Integer courseId;

    public CourseTimeResponse() {
        this.courseTimeList = new ArrayList<>();
    }
}
