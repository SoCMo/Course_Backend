package com.shu.course_backend.service;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.request.CourseRequest;
import com.shu.course_backend.model.request.CourseTimeModifyRequest;
import com.shu.course_backend.model.request.CourseTimeRequest;

/**
 * @ClassName: AdminService
 * @Description: 管理员模块接口
 * @Author: pongshy
 * @Date: 2021/4/25-19:26
 * @Version: V1.0
 **/
public interface AdminService {


    /**
     * @Description: 创建课程
     * @Param: [courseRequest]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/4/25
     * @Version: V1.0
     **/
    Result createCourse(CourseRequest courseRequest);

    /**
     * @Description: 获取所有课程
     * @Param: []
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/4/25
     * @Version: V1.0
     **/
    Result getAllCourses();

    /**
     * @Description: 删除一门课程
     * @Param: [courseId]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/4/25
     * @Version: V1.0
     **/
    Result deleteOneCourse(Integer courseId);

    /*
     * @Description: 增加一门课的上课时间与地点等数据
     * @Param: [courseTimeRequest]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/12
     * @Version: V1.0
     **/
    Result addCourseTime(CourseTimeRequest courseTimeRequest);

    /*
     * @Description: 修改一门课的上课时间与地点等数据
     * @Param: [courseTimeModifyRequest]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/12
     * @Version: V1.0
     **/
    Result modifyCourseTime(CourseTimeModifyRequest courseTimeModifyRequest);

    /*
     * @Description: 删除一门课程指定一个上课时间和地点等信息
     * @Param: [id]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/12
     * @Version: V1.0
     **/
    Result deleteCourseTime(Integer id);
}
