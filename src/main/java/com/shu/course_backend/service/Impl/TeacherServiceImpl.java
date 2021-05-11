package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.ConstDoMapper;
import com.shu.course_backend.dao.OpenDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.OpenDo;
import com.shu.course_backend.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: TeacherServiceImpl
 * @Description:
 * @Author: pongshy
 * @Date: 2021/5/5 22:06
 **/
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {



    @Resource
    private ConstDoMapper constDoMapper;

    @Resource
    private OpenDoMapper openDoMapper;

    /*
     * @Description: 申请课程
     * @Method: [courseId, userId]
     * @Return: com.shu.course_backend.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/5 22:14
     */
    @Override
    public Result applyTheCourseByTeacher(Integer courseId, Integer courseTimeId, String userId) {
        OpenDo record = new OpenDo();
        record.setCourseId(courseId);
        record.setCourseTimeId(courseTimeId);
        record.setTeacherId(userId);

        try {
            if (openDoMapper.insertSelective(record) == 1) {
                return Result.success("申请成功");
            } else {
                throw new AllException(EmAllException.DATABASE_ERROR);
            }
        } catch (AllException e) {
            return Result.error(e);
        }
    }
}
