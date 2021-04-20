package com.shu.course_backend.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserRole
 * @Description: 用户身份枚举类
 * @Author: pongshy
 * @Date: 2021/4/13 15:09
 **/
public enum UserRole {


    ROLE_STUDENT,
    ROLE_TEACHER,
    ROLE_ADMIN;

    public static List<String> getUserRole(String i) {
        List<String> roles = new ArrayList<>();

        if ("001".equals(i)) {
            roles.add(UserRole.ROLE_STUDENT.toString());
        } else if ("010".equals(i)) {
            roles.add(UserRole.ROLE_TEACHER.toString());
        } else if ("011".equals(i)) {
            roles.add(UserRole.ROLE_STUDENT.toString());
            roles.add(UserRole.ROLE_TEACHER.toString());
        } else if ("100".equals(i)) {
            roles.add(UserRole.ROLE_ADMIN.toString());
        } else if ("101".equals(i)) {
            roles.add(UserRole.ROLE_STUDENT.toString());
            roles.add(UserRole.ROLE_ADMIN.toString());
        } else if ("110".equals(i)) {
            roles.add(UserRole.ROLE_TEACHER.toString());
            roles.add(UserRole.ROLE_ADMIN.toString());
        } else if ("111".equals(i)) {
            roles.add(UserRole.ROLE_STUDENT.toString());
            roles.add(UserRole.ROLE_TEACHER.toString());
            roles.add(UserRole.ROLE_ADMIN.toString());
        }
        return roles;
    }



}
