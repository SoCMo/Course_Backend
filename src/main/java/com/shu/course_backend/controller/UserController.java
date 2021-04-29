package com.shu.course_backend.controller;

import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.request.UpdateIdentityReq;
import com.shu.course_backend.model.request.UpdatePasswordReq;
import com.shu.course_backend.model.request.UpdateUserInfoEntity;
import com.shu.course_backend.model.request.UserAdditionReq;
import com.shu.course_backend.service.UserService;
import com.shu.course_backend.tool.AuthTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

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
@Validated
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    @ApiOperation(value = "创建用户", notes = "默认密码为123456")
    Result UserAddition(@RequestBody @Validated UserAdditionReq userAdditionReq) {
        return userService.UserAddition(userAdditionReq);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    @ApiOperation(value = "删除用户")
    Result UserDeletion(@ApiParam(value = "用户Id", required = true) @PathVariable
                        @NotBlank(message = "用户名不能为空") String userId) {
        return userService.UserDeletion(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/identity/{userId}")
    @ApiOperation(value = "更新用户身份")
    @ApiImplicitParam(name = "identity", value = "用户身份，共3位二进制(001学生，010教师，100系统管理员)"
            ,required = true, dataType = "string")
    Result UpdateIdentity(@ApiParam(value = "用户Id", required = true)
                          @NotBlank(message = "用户名不能为空") @PathVariable("userId") String userId,
                          @RequestBody @Validated UpdateIdentityReq updateIdentityReq){
        updateIdentityReq.setUserId(userId);

        return userService.UpdateIdentity(updateIdentityReq);
    }

    @PatchMapping("/password/{userId}")
    @ApiOperation(value = "更新用户密码")
    @ApiImplicitParam(name = "password", value = "例:123456", required = true, dataType = "string")
    Result UpdatePassword(@ApiParam(value = "用户Id", required = true) @NotBlank(message = "用户名不能为空") @PathVariable("userId") String userId,
                          @RequestBody @Validated UpdatePasswordReq updatePasswordReq){
        updatePasswordReq.setUserId(userId);

        return userService.UpdatePassword(updatePasswordReq);
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "更新用户信息")
    Result UpdateUserInfo(@RequestBody @Validated UpdateUserInfoEntity updateUserInfoEntity,
                          @PathVariable("userId") @NotBlank(message = "用户Id不能为空") String userId){
        updateUserInfoEntity.setUserId(userId);

        if(authTool.getUserIdentity().contains("ROLE_ADMIN")
                || authTool.getUserIdentity().contains("ROLE_TEACHER")
                || userId.equals(authTool.getUserId())){
            return userService.UpdateUserInfo(updateUserInfoEntity);
        }

        return Result.error(new AllException(EmAllException.IDENTITY_ERROR, "没有权限"));
    }
}
