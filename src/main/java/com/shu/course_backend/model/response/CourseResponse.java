package com.shu.course_backend.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CourseResponse
 * @Description: 课程信息返回类
 * @Author: pongshy
 * @Date: 2021/5/11-19:44
 * @Version: V1.0
 **/
@Data
public class CourseResponse {


    private Integer id;

    private String name;

    private Integer credit;

    private Integer capacity;

    private Integer proportion;

    private Integer isChosen;   // 1->被选择，0->未被选择

    private List<CourseTimeResponse> timeList;

    public CourseResponse() {
        this.timeList = new ArrayList<>();
    }
}
