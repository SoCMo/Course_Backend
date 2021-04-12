package com.shu.course_backend.service;

import org.springframework.http.ResponseEntity;

/**
 * @ClassName: LoginService
 * @Description: 登录接口类
 * @Author: pongshy
 * @Date: 2021/1/8-10:37
 * @Version: V1.0
 **/
public interface LoginService {


    public ResponseEntity<?> login(String username, String password) throws Exception;

    public ResponseEntity<?> testGetMsgFromToken(String token);
}
