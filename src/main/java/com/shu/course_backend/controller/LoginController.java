package com.shu.course_backend.controller;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.request.JwtRequest;
import com.shu.course_backend.model.request.RegisterRequest;
import com.shu.course_backend.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: LoginController
 * @Description: 登录接口
 * @Author: pongshy
 * @Date: 2021/1/8-10:37
 * @Version: V1.0
 **/
@RestController
@CrossOrigin
@Api(tags = "登录模块")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口")
    public Result loginByUsernameAndPassword(@RequestBody JwtRequest jwtRequest) throws Exception {
        return loginService.login(jwtRequest.getUsername(), jwtRequest.getPassword());
    }

    @PostMapping(value = "register")
    @ApiOperation(value = "注册接口")
    public Result registerUser(@RequestBody @Validated RegisterRequest registerRequest) {
        return loginService.register(registerRequest);
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ApiOperation(value = "测试接口")
    public ResponseEntity<?> test1(@RequestParam(value = "word") String word,
                                   HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");
        return loginService.testGetMsgFromToken(jwtToken.substring(7));
    }


}
