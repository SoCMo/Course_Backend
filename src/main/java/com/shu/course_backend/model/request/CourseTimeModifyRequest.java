package com.shu.course_backend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName: CourseTimeModifyRequest
 * @Description: 上课时间与地点等信息修改接受类
 * @Author: pongshy
 * @Date: 2021/5/12-16:22
 * @Version: V1.0
 **/
@Data
@ApiModel(value = "上课时间与地点等信息修改接受类", description = "上课时间与地点等信息修改接受类")
public class CourseTimeModifyRequest {


    @NotNull(message = "主键id不能为空")
    @ApiModelProperty(value = "主键id", required = true, example = "1")
    private Integer id;

    @NotEmpty(message = "上课地点不能为空")
    @ApiModelProperty(value = "上课地点", required = true, example = "A301")
    private String address;

    @NotEmpty(message = "上课时间不能为空")
    @ApiModelProperty(value = "上课时间", required = true, example = "周二:3-4")
    private List<String> courseTime;

    @NotEmpty(message = "答疑地点不能为空")
    @ApiModelProperty(value = "答疑地点", required = true, example = "计704")
    private String answerAddress;

    @NotEmpty(message = "答疑时间不能为空")
    @ApiModelProperty(value = "答疑时间", required = true)
    private String answerTime;

}
