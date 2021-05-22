package com.shu.course_backend.model.response;

import lombok.Data;

/**
 * @ClassName: GradeResponse
 * @Description: 成绩返回类
 * @Author: pongshy
 * @Date: 2021/5/15 22:09
 **/
@Data
public class GradeResponse {


    private String studentId;

    private String name;

    private Integer usual;

    private Integer exam;

    private Double grade;

    private Integer proportion; //平时成绩占比

    private String department;

    private Double gradePoint;
}
