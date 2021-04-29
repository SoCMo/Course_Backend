package com.shu.course_backend.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
* @program: UpdateUserInfoEntity
* @Description: 用户信息修改请求体
* @Author: SoCMo
* @Date: 2021/4/28
*/
@Data
@ApiModel(value = "用户信息修改请求体", description = "用户信息修改请求体")
public class UpdateUserInfoEntity {
    @JsonIgnore
    private String userId;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true, example = "1xxxxxxxxxx")
    private String mobilePhone;

    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式错误")
    @ApiModelProperty(value = "邮箱", required = true, example = "socmo@qq.com")
    private String email;
}
