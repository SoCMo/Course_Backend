package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.*;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.*;
import com.shu.course_backend.model.request.CourseRequest;
import com.shu.course_backend.model.request.CourseTimeModifyRequest;
import com.shu.course_backend.model.request.CourseTimeRequest;
import com.shu.course_backend.model.response.CourseResponse;
import com.shu.course_backend.model.response.CourseTimeResponse;
import com.shu.course_backend.service.AdminService;
import com.shu.course_backend.tool.CourseTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private final CourseTimeDoMapper courseTimeDoMapper;

    private final OpenDoMapper openDoMapper;

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
        List<CourseResponse> responses = new ArrayList<>();

        for (CourseDo courseDo : courseDoList) {
            CourseResponse tmp = new CourseResponse();

            BeanUtils.copyProperties(courseDo, tmp);

            CourseTimeDoExample example = new CourseTimeDoExample();
            example
                    .createCriteria()
                    .andCourseIdEqualTo(courseDo.getId());
            List<CourseTimeDo> timeDoList = courseTimeDoMapper.selectByExample(example);
            List<CourseTimeResponse> timeResponses = new ArrayList<>();
            // 是否已被选择
            OpenDoExample openDoExample1 = new OpenDoExample();
            openDoExample1
                    .createCriteria()
                    .andCourseIdEqualTo(courseDo.getId());
            Integer ct = openDoMapper.countByExample(openDoExample1);
            if (ct == 0) {
                tmp.setIsChosen(0);
            } else {
                tmp.setIsChosen(1);
            }

            // 上课时间
            for (CourseTimeDo courseTimeDo : timeDoList) {
                CourseTimeResponse resTmp = new CourseTimeResponse();
                BeanUtils.copyProperties(courseTimeDo, resTmp);
                resTmp.setCourseTimeList(CourseTool.translateFromBitToStr(courseTimeDo.getCourseTime()));
                //查看该时间段老师是否已开课
                Integer id = courseTimeDo.getId();
                OpenDoExample openDoExample = new OpenDoExample();
                openDoExample
                        .createCriteria()
                        .andCourseTimeIdEqualTo(id);
                Integer count = openDoMapper.countByExample(openDoExample);
                if (count == 0) {
                    resTmp.setIsChosen(0);
                } else {
                    resTmp.setIsChosen(1);
                }

                timeResponses.add(resTmp);
            }
            tmp.setTimeList(timeResponses);
            responses.add(tmp);
        }

        return Result.success(responses);
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
            OpenDoExample openDoExample = new OpenDoExample();
            openDoExample
                    .createCriteria()
                    .andCourseIdEqualTo(courseId);
            Integer count = openDoMapper.countByExample(openDoExample);
            if (count == 0) {
                if (courseDoMapper.deleteByPrimaryKey(courseId) == 1) {
                    return Result.success("删除成功");
                } else {
                    throw new AllException(EmAllException.DATABASE_ERROR, "课程id错误");
                }
            } else {
                throw new AllException(EmAllException.BAD_REQUEST, "请先删除时间");
            }
        } catch (AllException ex) {
            return Result.error(ex);
        }
    }

    /*
     * @Description: 增加一门课的上课时间与地点等数据
     * @Param: [courseTimeRequest]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/12
     * @Version: V1.0
     **/
    @Override
    public Result addCourseTime(CourseTimeRequest courseTimeRequest) {
        CourseTimeDo record = new CourseTimeDo();

        record.setCourseId(courseTimeRequest.getCourseId());
        record.setAddress(courseTimeRequest.getAddress());
        record.setAnswerAddress(courseTimeRequest.getAnswerAddress());
        record.setAnswerTime(courseTimeRequest.getAnswerTime());
        try {
            record.setCourseTime(CourseTool.translateFromStrToBit(courseTimeRequest.getCourseTime()));
        } catch (AllException e) {
            return Result.error(e);
        }

        try {
            CourseDoExample courseDoExample = new CourseDoExample();
            courseDoExample
                    .createCriteria()
                    .andIdEqualTo(courseTimeRequest.getCourseId());
            Integer count = courseDoMapper.countByExample(courseDoExample);
            if (count == 1) {
                if (courseTimeDoMapper.insertSelective(record) == 1) {
                    return Result.success("添加成功");
                } else {
                    throw new AllException(EmAllException.DATABASE_ERROR);
                }
            } else {
                throw new AllException(EmAllException.BAD_REQUEST, "参数有误");
            }
        } catch (AllException e) {
            return Result.error(e);
        }
    }

    /*
     * @Description: 修改一门课的上课时间与地点等数据
     * @Param: [courseTimeModifyRequest]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/12
     * @Version: V1.0
     **/
    @Override
    public Result modifyCourseTime(CourseTimeModifyRequest courseTimeModifyRequest) {
        CourseTimeDo record = new CourseTimeDo();

        record.setId(courseTimeModifyRequest.getId());
        record.setAddress(courseTimeModifyRequest.getAddress());
        record.setAnswerTime(courseTimeModifyRequest.getAnswerTime());
        record.setAnswerAddress(courseTimeModifyRequest.getAnswerAddress());
        try {
            record.setCourseTime(CourseTool.translateFromStrToBit(courseTimeModifyRequest.getCourseTime()));
        } catch (AllException e) {
            return Result.error(e);
        }

        try {
            if (courseTimeDoMapper.updateByPrimaryKeySelective(record) == 1) {
                return Result.success("修改成功");
            } else {
                throw new AllException(EmAllException.BAD_REQUEST, "传入参数有误");
            }
        } catch (AllException e) {
            return Result.error(e);
        }

    }

    /*
     * @Description: 删除一门课程指定一个上课时间和地点等信息
     * @Param: [id]
     * @return: com.shu.course_backend.model.Result
     * @Author: pongshy
     * @Date: 2021/5/12
     * @Version: V1.0
     **/
    @Override
    public Result deleteCourseTime(Integer id) {
        try {
            OpenDoExample openDoExample = new OpenDoExample();
            openDoExample
                    .createCriteria()
                    .andCourseTimeIdEqualTo(id);
            Integer count = openDoMapper.countByExample(openDoExample);

            if (count == 0) {
                if (courseTimeDoMapper.deleteByPrimaryKey(id) == 1) {
                    return Result.success("删除成功");
                } else {
                    throw new AllException(EmAllException.BAD_REQUEST, "传入id有误");
                }
            } else {
                throw new AllException(EmAllException.BAD_REQUEST, "该时间已被教师选择");
            }
        } catch (AllException e) {
            return Result.error(e);
        }
    }

}
