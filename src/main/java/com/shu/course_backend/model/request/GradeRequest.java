package com.shu.course_backend.model.request;

import com.shu.course_backend.model.Grade;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName: GradeRequest
 * @Description: 成绩接受类
 * @Author: pongshy
 * @Date: 2021/5/16 21:53
 **/
@Data
@ApiModel(value = "成绩接受类", description = "成绩接受类")
public class GradeRequest {


    @NotNull(message = "开课号不能为空")
    @ApiModelProperty(value = "开课号", required = true, example = "10")
    private Integer openId;

    @NotEmpty(message = "成绩列表不能为空")
    @ApiModelProperty(value = "成绩列表", required = true)
    private List<Grade> gradeList;

}
