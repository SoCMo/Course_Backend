package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;

/**
 * program: UserService
 * description: 用户通用接口
 * author: SoCMo
 * create: 2021/4/23 20:06
 */
public interface UserService {
    Result UserInfo(String userId);
}
