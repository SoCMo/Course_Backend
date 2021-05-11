package com.shu.course_backend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: ApplyRequest
 * @Description: 教师申请课程接受类
 * @Author: pongshy
 * @Date: 2021/5/11-18:58
 * @Version: V1.0
 **/
@Data
@ApiModel(value = "教师申请课程接受类", description = "教师申请课程接受类")
public class ApplyRequest {


    @NotNull(message = "课程号不能为空")
    @ApiModelProperty(value = "课程号", required = true)
    private Integer courseId;

    @NotNull(message = "课程时间Id不能为空")
    @ApiModelProperty(value = "课程时间Id", required = true)
    private Integer courseTimeId;
}
