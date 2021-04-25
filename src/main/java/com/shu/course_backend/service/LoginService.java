package com.shu.course_backend.service;

import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

/**
 * @ClassName: LoginService
 * @Description: 登录接口类
 * @Author: pongshy
 * @Date: 2021/1/8-10:37
 * @Version: V1.0
 **/
public interface LoginService {

    /**
     * @Description: 登录接口实现
     * @Param: [username, password]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: pongshy
     * @Date: 2021/4/20
     * @Version: V1.0
     **/
    public Result login(String username, String password) throws Exception;

    public ResponseEntity<?> testGetMsgFromToken(String token);

    /**
     * @Description: 新用户注册接口实现
     * @Param: [registerRequest]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: pongshy
     * @Date: 2021/4/20
     * @Version: V1.0
     **/
    public Result register(RegisterRequest registerRequest);
}
