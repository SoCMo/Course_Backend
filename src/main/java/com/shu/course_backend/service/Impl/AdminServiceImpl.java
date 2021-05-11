package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.CourseDoMapper;
import com.shu.course_backend.dao.DepartmentDoMapper;
import com.shu.course_backend.dao.UserDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.CourseDo;
import com.shu.course_backend.model.request.CourseRequest;
import com.shu.course_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: AdminServiceImpl
 * @Description: 管理员模块接口实现
 * @Author: pongshy
 * @Date: 2021/4/25-19:27
 * @Version: V1.0
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final DepartmentDoMapper departmentDoMapper;

    private final UserDoMapper userDoMapper;

    private final CourseDoMapper courseDoMapper;

    /**
     * @Description: 创建课程
     * @Param: [courseRequest]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/4/25
     * @Version: V1.0
     **/
    @Override
    public Result createCourse(CourseRequest courseRequest) {
        CourseDo courseDo = new CourseDo();

        courseDo.setName(courseRequest.getCourseName());
        courseDo.setCredit(courseRequest.getCredit());
        courseDo.setCapacity(courseRequest.getCapacity());
        courseDo.setProportion(courseRequest.getProportion());
        try {
            if (courseDoMapper.insertSelective(courseDo) == 1) {
                return Result.success("创建成功");
            } else {
                throw new AllException(EmAllException.INTERNAL_ERROR, "创建失败");
            }
        } catch (AllException ex) {
            return Result.error(ex);
        }
    }

    /**
     * @Description: 获取所有课程
     * @Param: []
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/4/25
     * @Version: V1.0
     **/
    @Override
    public Result getAllCourses() {
        List<CourseDo> courseDoList = courseDoMapper.selectByExample(null);

        return Result.success(courseDoList);
    }

    /**
     * @Description: 删除一门课程
     * @Param: [courseId]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/4/25
     * @Version: V1.0
     **/
    @Override
    public Result deleteOneCourse(Integer courseId) {
        try {
            if (courseDoMapper.deleteByPrimaryKey(courseId) == 1) {
                return Result.success("删除成功");
            } else {
                throw new AllException(EmAllException.DATABASE_ERROR, "课程id错误");
            }
        } catch (AllException ex) {
            return Result.error(ex);
        }
    }

}
