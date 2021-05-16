package com.shu.course_backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: Grade
 * @Description: 成绩类
 * @Author: pongshy
 * @Date: 2021/5/16 21:54
 **/
@Data
@ApiModel(value = "成绩类", description = "成绩类")
public class Grade {


    @NotBlank(message = "学生学号")
    @ApiModelProperty(value = "学生学号", required = true, example = "18120198")
    private String studentId;

    @NotNull(message = "平时成绩不能为空")
    @ApiModelProperty(value = "平时成绩", required = true, example = "88")
    private Integer usual;

    @NotNull(message = "考试成绩不能为空")
    @ApiModelProperty(value = "考试成绩", required = true, example = "90")
    private Integer exam;
}
