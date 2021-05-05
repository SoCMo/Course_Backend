package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;

/**
 * program: ConfigService
 * description: 参数实现层
 * author: SoCMo
 * create: 2021/4/27 16:12
 */
public interface ConfigService {
    /**
    * @Description: 更新学期
    * @Param: [nowSemester]
    * @Return: com.shu.course_backend.model.Result
    * @Author: SoCMo
    * @Date: 2021/4/28
    */
    Result updateSemester(String nowSemester);

    /**
    * @Description: 更新选课状态
    * @Param: [State]
    * @Return: com.shu.course_backend.model.Result
    * @Author: SoCMo
    * @Date: 2021/4/28
    */
    Result updateElectionState(String State);
}
