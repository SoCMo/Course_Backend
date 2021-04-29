package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.DepartmentDoMapper;
import com.shu.course_backend.dao.PasswordDoMapper;
import com.shu.course_backend.dao.UserDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.UserRole;
import com.shu.course_backend.model.entity.*;
import com.shu.course_backend.model.request.UpdateIdentityReq;
import com.shu.course_backend.model.request.UpdatePasswordReq;
import com.shu.course_backend.model.request.UpdateUserInfoEntity;
import com.shu.course_backend.model.request.UserAdditionReq;
import com.shu.course_backend.model.response.Info.UserInfoRes;
import com.shu.course_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * program: UserServiceImpl
 * description: 普通用户通用接口实现类
 * author: SoCMo
 * create: 2021/4/23 20:07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDoMapper userDoMapper;

    private final DepartmentDoMapper departmentDoMapper;

    private final PasswordDoMapper passwordDoMapper;

    @Override
    public Result UserInfo(String userId) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(userId);
        if(userDo == null){
            return Result.error(EmAllException.BAD_REQUEST, "该用户不存在");
        }

        UserInfoRes response = new UserInfoRes();
        BeanUtils.copyProperties(userDo, response);
        response.setGender(userDo.getGender() == 1 ? "女" : "男");
        response.setIdentity(UserRole.getUserRole(userDo.getIdentity()));
        response.setDepartment(
                departmentDoMapper
                        .selectByPrimaryKey(userDo.getDepartmentId())
                        .getDepartmentName()
        );

        return Result.success(response);
    }


    @Override
    public Result UserAddition(UserAdditionReq userAdditionReq) {
        if (departmentDoMapper.selectByPrimaryKey(userAdditionReq.getDepartmentId()) == null) {
            return Result.error(new AllException(EmAllException.BAD_REQUEST, "该部门不存在"));
        }

        if(userDoMapper.selectByPrimaryKey(userAdditionReq.getUserId()) != null){
            return Result.error(new AllException(EmAllException.BAD_REQUEST, "该用户Id已存在"));
        }

        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userAdditionReq, userDo);
        try {
            if(userDoMapper.insertSelective(userDo) >= 1){
                PasswordDo passwordDo = new PasswordDo();
                passwordDo.setUserId(userAdditionReq.getUserId());
                passwordDo.setPassword(new BCryptPasswordEncoder().encode("123456"));
                if(passwordDoMapper.updateByPrimaryKeySelective(passwordDo) >= 1){
                    return Result.success(userDo);
                }
            }

            throw new AllException(EmAllException.DATABASE_ERROR);
        }catch (AllException ex){
            return Result.error(ex);
        }
    }

    @Override
    public Result UserDeletion(String userId) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(userId);
        if(userDo == null){
            return Result.error(EmAllException.BAD_REQUEST, "该用户不存在");
        }

        try {
            if(userDoMapper.deleteByPrimaryKey(userId) == 1){
                return Result.success();
            }else {
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
        }catch (AllException ex){
            return Result.error(ex);
        }
    }

    @Override
    public Result UpdatePassword(UpdatePasswordReq updatePasswordReq) {
        PasswordDo passwordDo = passwordDoMapper.selectByPrimaryKey(updatePasswordReq.getUserId());
        if(passwordDo == null){
            return Result.error(EmAllException.BAD_REQUEST, "该用户不存在");
        }

        passwordDo.setPassword(new BCryptPasswordEncoder().encode(updatePasswordReq.getPassword()));
        try {
            if(passwordDoMapper.updateByPrimaryKeySelective(passwordDo) >= 1){
                return Result.success();
            }else {
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
        }catch (AllException ex){
            return Result.error(ex);
        }
    }

    @Override
    public Result UpdateIdentity(UpdateIdentityReq updateIdentityReq) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(updateIdentityReq.getUserId());
        if(userDo == null){
            return Result.error(EmAllException.BAD_REQUEST, "该用户不存在");
        }

        userDo.setIdentity(updateIdentityReq.getIdentity());
        try {
            if(userDoMapper.updateByPrimaryKeySelective(userDo) >= 1){
                return Result.success(userDoMapper.selectByPrimaryKey(updateIdentityReq.getUserId()));
            }else {
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
        }catch (AllException ex){
            return Result.error(ex);
        }
    }

    @Override
    public Result UpdateUserInfo(UpdateUserInfoEntity updateUserInfoEntity) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(updateUserInfoEntity.getUserId());
        if(userDo == null){
            return Result.error(EmAllException.BAD_REQUEST, "该用户不存在");
        }

        userDo.setMobilePhone(updateUserInfoEntity.getMobilePhone());
        userDo.setEmail(updateUserInfoEntity.getEmail());
        try {
            if(userDoMapper.updateByPrimaryKeySelective(userDo) >= 1){
                return Result.success(userDoMapper.selectByPrimaryKey(updateUserInfoEntity.getUserId()));
            }else {
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
        }catch (AllException ex){
            return Result.error(ex);
        }
    }

}
