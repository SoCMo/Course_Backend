package com.shu.course_backend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: GradeModifyRequest
 * @Description: 成绩修改类
 * @Author: pongshy
 * @Date: 2021/5/19-14:51
 * @Version: V1.0
 **/
@Data
@ApiModel(value = "成绩修改类", description = "成绩修改类")
public class GradeModifyRequest {


    @NotNull(message = "课程号不能为空")
    @ApiModelProperty(value = "课程号", required = true, example = "10")
    private Integer courseId;

    @NotBlank(message = "学生学号")
    @ApiModelProperty(value = "学生学号", required = true, example = "18120198")
    private String studentId;

    @NotNull(message = "平时成绩不能为空")
    @ApiModelProperty(value = "平时成绩", required = true, example = "88")
    @Min(value = 0, message = "平时成绩不能小于0")
    @Max(value = 100, message = "平时成绩不能大于100")
    private Integer usual;

    @NotNull(message = "考试成绩不能为空")
    @ApiModelProperty(value = "考试成绩", required = true, example = "90")
    @Min(value = 0, message = "考试成绩不能小于0")
    @Max(value = 100, message = "考试成绩不能大于100")
    private Integer exam;
}
