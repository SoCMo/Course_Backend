package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;

/**
 * program: StudentService
 * description: 学生资源
 * author: SoCMo
 * create: 2021/5/15 11:48
 */
public interface StudentService {
    /**
     * @Description: 选课
     * @Param: [openId]
     * @Return: com.shu.course_backend.model.Result
     * @Author: SoCMo
     * @Date: 2021/5/18
     */
    Result selectCourse(Integer openId);

    /**
     * @Description: 获取选课列表
     * @Param: [semester]
     * @Return: com.shu.course_backend.model.Result
     * @Author: SoCMo
     * @Date: 2021/5/18
     */
    Result selectionList(String semester);

    /**
    * @Description: 获取所有选课列表
    * @Param: []
    * @Return: com.shu.course_backend.model.Result
    * @Author: SoCMo
    * @Date: 2021/5/23
    */
    Result allSelectionList();

    /**
     * @Description: 退课
     * @Param: [openId]
     * @Return: com.shu.course_backend.model.Result
     * @Author: SoCMo
     * @Date: 2021/5/18
     */
    Result quiteCourse(Integer openId);
}
