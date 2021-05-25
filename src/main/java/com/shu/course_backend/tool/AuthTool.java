package com.shu.course_backend.tool;

import com.shu.course_backend.dao.UserDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.entity.UserDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 上海大学登录接口
 * @author: 0GGmr0
 * @create: 2019-12-01 21:40
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthTool {
    private final UserDoMapper userDoMapper;

    /**
     * @Description: 获取当前用户对象
     * @Param: []
     * @Return: com.spring.CourseElection.model.entity.UserDo
     * @Author: SoCMo
     * @Date: 2021/1/4
     */
    public UserDo getUser() throws AllException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDo userDo = userDoMapper.selectByPrimaryKey(userId);
        if (userDo == null) {
            throw new AllException(EmAllException.DATABASE_ERROR, "凭证对应的用户在系统中不存在！");
        }
        return userDo;
    }

    /**
     * @Description: 获取当前用户id
     * @Param: []
     * @Return: java.lang.String
     * @Author: SoCMo
     * @Date: 2021/1/6
     */
    public String getUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * @Description: 获取用户身份
     * @Param: []
     * @Return: java.util.List<java.lang.String>
     * @Author: SoCMo
     * @Date: 2021/4/25
     */
    public List<String> getUserIdentity() {
        return SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}

