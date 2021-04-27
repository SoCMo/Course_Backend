package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;

/**
 * program: ConfigService
 * description: 参数实现层
 * author: SoCMo
 * create: 2021/4/27 16:12
 */
public interface ConfigService {
    Result updateSemester(String nowSemester);
}
