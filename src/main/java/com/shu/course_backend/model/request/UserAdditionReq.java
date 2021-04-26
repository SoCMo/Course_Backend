package com.shu.course_backend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
* @program: UserAdditionReq
* @Description: 添加用户请求体
* @Author: SoCMo
* @Date: 2021/4/26
*/
@Data
@ApiModel(value = "用户创建请求体", description = "用户创建请求体")
public class UserAdditionReq {
    @NotBlank(message = "用户Id不能为空")
    @ApiModelProperty(value = "用户Id", required = true, example = "1xxxxxxx")
    private String userId;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true, example = "嘉然")
    private String name;

    @NotNull(message = "性别不能为空")
    @ApiModelProperty(value = "性别", required = true, example = "0为男，1为女")
    private Integer gender;

    @NotNull(message = "部门号不能为空")
    @ApiModelProperty(value = "部门Id", required = true, example = "2")
    private Integer departmentId;

    @NotBlank(message = "学历不能为空")
    @ApiModelProperty(value = "学历", required = true, example = "本科生")
    private String education;

    @Pattern(regexp = "^[01]{3}$", message = "身份格式错误")
    @ApiModelProperty(value = "用户身份", required = true, example = "共3位二进制(001学生，010教师，100系统管理员)")
    private String identity;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true, example = "1xxxxxxxxxx")
    private String mobilePhone;

    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式错误")
    @ApiModelProperty(value = "邮箱", required = true, example = "socmo@qq.com")
    private String email;
}
