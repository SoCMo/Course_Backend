package com.shu.course_backend.model.response;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassName: LoginResponse
 * @Description: 登录接口返回类
 * @Author: pongshy
 * @Date: 2021/4/20-18:34
 * @Version: V1.0
 **/
@Data
public class LoginResponse {


    @NotEmpty
    private String token;

    private String userId;

    private String username;

    private String education;

    private String department;

    private List<String> roles;

    private String phone;

    private String email;

    private String semester;

}
