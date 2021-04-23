package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.DepartmentDoMapper;
import com.shu.course_backend.dao.UserDoMapper;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.UserRole;
import com.shu.course_backend.model.entity.UserDo;
import com.shu.course_backend.model.response.Info.UserInfoRes;
import com.shu.course_backend.model.response.LoginResponse;
import com.shu.course_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public Result UserInfo(String userId) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(userId);
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
}
