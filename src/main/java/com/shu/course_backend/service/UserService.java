package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.request.UserAdditionReq;

/**
 * program: UserService
 * description: 用户通用接口
 * author: SoCMo
 * create: 2021/4/23 20:06
 */
public interface UserService {
    /**
    * @Description: 获取用户信息
    * @Param: [userId]
    * @Return: com.shu.course_backend.model.Result
    * @Author: SoCMo
    * @Date: 2021/4/26
    */
    Result UserInfo(String userId);


    /**
     * @Description: 添加用户
     * @Param: [userAdditionReq]
     * @Return: com.shu.course_backend.model.Result
     * @Author: SoCMo
     * @Date: 2021/4/26
     */
    Result UserAddition(UserAdditionReq userAdditionReq);

}
