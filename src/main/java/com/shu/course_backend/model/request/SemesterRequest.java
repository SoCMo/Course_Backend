package com.shu.course_backend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: SemesterRequest
 * @Description: 学期接受类
 * @Author: pongshy
 * @Date: 2021/5/18-19:58
 * @Version: V1.0
 **/
@Data
@ApiModel(value = "学期结束类", description = "学期接受类")
public class SemesterRequest {


    @NotBlank(message = "学期不能为空")
    @ApiModelProperty(value = "学期", required = true, example = "2021学年秋季学期")
    private String semester;
}
