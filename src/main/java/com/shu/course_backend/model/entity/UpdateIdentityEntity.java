package com.shu.course_backend.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
* @program: UpdateIdentityReq
* @Description: 权限修改请求体
* @Author: SoCMo
* @Date: 2021/4/28
*/
@Data
public class UpdateIdentityEntity {
    private String userId;

    private String identity;
}
