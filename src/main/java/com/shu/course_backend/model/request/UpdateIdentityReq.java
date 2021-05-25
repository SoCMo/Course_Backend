package com.shu.course_backend.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @program: UpdateIdentityReq
 * @Description: 权限修改请求体
 * @Author: SoCMo
 * @Date: 2021/4/28
 */
@Data
@ApiModel(value = "权限修改请求体", description = "权限修改请求体")
public class UpdateIdentityReq {
    @JsonIgnore
    private String userId;

    @Pattern(regexp = "^[01]{3}$", message = "身份格式错误")
    @ApiModelProperty(value = "用户身份", required = true, example = "共3位二进制(001学生，010教师，100系统管理员)")
    private String identity;
}
