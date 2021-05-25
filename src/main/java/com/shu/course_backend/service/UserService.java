package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.request.UpdateIdentityReq;
import com.shu.course_backend.model.request.UpdatePasswordReq;
import com.shu.course_backend.model.request.UpdateUserInfoEntity;
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

    /**
     * @Description: 删除用户
     * @Param: [userId]
     * @Return: com.shu.course_backend.model.Result
     * @Author: SoCMo
     * @Date: 2021/4/29
     */
    Result UserDeletion(String userId);

    /**
     * @Description: 更新密码
     * @Param: [updatePasswordEntity]
     * @Return: com.shu.course_backend.model.Result
     * @Author: SoCMo
     * @Date: 2021/4/29
     */
    Result UpdatePassword(UpdatePasswordReq updatePasswordReq);

    /**
     * @Description: 修改用户身份
     * @Param: [updateIdentityReq]
     * @Return: com.shu.course_backend.model.Result
     * @Author: SoCMo
     * @Date: 2021/4/28
     */
    Result UpdateIdentity(UpdateIdentityReq updateIdentityReq);

    Result UpdateUserInfo(UpdateUserInfoEntity updateUserInfoEntity);
}
