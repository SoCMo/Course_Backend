package com.shu.course_backend.controller;

import com.shu.course_backend.dao.UserDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.UserRole;
import com.shu.course_backend.model.request.UserAdditionReq;
import com.shu.course_backend.service.AdminService;
import com.shu.course_backend.service.UserService;
import com.shu.course_backend.tool.AuthTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * program: UserController
 * description: 普通用户资源
 * author: SoCMo
 * create: 2021/4/23 20:27
 */
@RestController
@RequestMapping("/users")
@Api(tags = "普通用户资源")
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
        if(authTool.getUserIdentity().contains("ROLE_ADMIN")
        || authTool.getUserIdentity().contains("ROLE_TEACHER")
        || userId.equals(authTool.getUserId())){
            return userService.UserInfo(userId);
        }

        return Result.error(new AllException(EmAllException.IDENTITY_ERROR, "没有权限"));
    }

    @PostMapping("/")
    @ApiOperation(value = "创建用户")
    Result UserAddition(@RequestBody @Validated UserAdditionReq userAdditionReq) {
        System.out.println(authTool.getUserIdentity());
        return userService.UserAddition(userAdditionReq);
    }
}
