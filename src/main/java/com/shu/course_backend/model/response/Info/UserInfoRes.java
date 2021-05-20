package com.shu.course_backend.model.response.Info;

import lombok.Data;

import java.util.List;

/**
 * program: UserInfoRes
 * description: 用户信息返回体
 * author: SoCMo
 * create: 2021/4/23 20:08
 */
@Data
public class UserInfoRes {
    private String userId;

    private String name;

    private String gender;

    private String department;

    private String education;

    private List<String> identity;

    private String mobilePhone;

    private String email;

    private Boolean pending;
}
