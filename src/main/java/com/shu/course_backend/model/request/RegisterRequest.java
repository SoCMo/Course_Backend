package com.shu.course_backend.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: RegisterRequest
 * @Description: 注册接口接受类
 * @Author: pongshy
 * @Date: 2021/4/20-19:22
 * @Version: V1.0
 **/
@Data
public class RegisterRequest {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private Integer departmentId;

    private String education;

    private String phone;

    @Email
    private String email;
}
