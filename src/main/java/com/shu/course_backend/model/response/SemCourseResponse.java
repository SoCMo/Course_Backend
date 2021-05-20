package com.shu.course_backend.model.response;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: SemCourseResponse
 * @Description: 指定学期开课情况
 * @Author: pongshy
 * @Date: 2021/5/18-19:16
 * @Version: V1.0
 **/
@Data
public class SemCourseResponse {


    private Integer courseId;

    private Integer openid;

    private String name;

    private Integer credit;

    private Integer capacity;

    private String choseAndAll;

    private List<String> courseTimeList;

    private String address;

    private String answerAddress;

    private String answerTime;


}
