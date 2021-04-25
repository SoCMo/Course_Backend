package com.shu.course_backend.controller;

import com.shu.course_backend.dao.UserDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.UserRole;
import com.shu.course_backend.service.UserService;
import com.shu.course_backend.tool.AuthTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * program: UserController
 * description: 普通用户控制层
 * author: SoCMo
 * create: 2021/4/23 20:27
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthTool authTool;

    @GetMapping("/{userId}")
    @ApiOperation(value = "获取用户信息")
    Result UserInfo(@PathVariable("userId") String userId) {
        if(StringUtils.isEmpty(userId)){
            return Result.error(new AllException(EmAllException.BAD_REQUEST, "用户名不能为空"));
        }
        if(!authTool.getUserIdentity().contains("ROLE_STUDENT")
        && !authTool.getUserIdentity().contains("ROLE_TEACHER")
        && !userId.equals(authTool.getUserId())){
            return Result.error(new AllException(EmAllException.IDENTITY_ERROR, "没有权限"));
        }


        return userService.UserInfo(userId);
    }
}
