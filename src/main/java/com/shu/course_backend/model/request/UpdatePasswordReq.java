package com.shu.course_backend.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
* @program: UpdatePasswordReq
* @Description: 密码修改请求体
* @Author: SoCMo
* @Date: 2021/4/28
*/
@Data
@ApiModel(value = "密码修改请求体", description = "密码修改请求体")
public class UpdatePasswordReq {
    @JsonIgnore
    private String userId;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true, example = "xxxxxxx")
    private String password;
}
