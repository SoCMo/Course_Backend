package com.shu.course_backend.model;

/**
 * @ClassName: UserRole
 * @Description: 用户身份枚举类
 * @Author: pongshy
 * @Date: 2021/4/13 15:09
 **/
public enum UserRole {


    ROLE_USER,
    ROLE_TEACHER,
    ROLE_ADMIN;

    public static String getUserRole(Byte i) {
        switch (i) {
            case 0: {
                return UserRole.ROLE_USER.toString();
            }
            case 1: {
                return UserRole.ROLE_TEACHER.toString();
            }
            case 2: {
                return UserRole.ROLE_ADMIN.toString();
            }
            default: {
                return "身份出错";
            }
        }
    }



}
