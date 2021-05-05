package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.ApplicationDoMapper;
import com.shu.course_backend.dao.ConstDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.ApplicationDo;
import com.shu.course_backend.model.entity.ApplicationDoExample;
import com.shu.course_backend.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    private ApplicationDoMapper applicationDoMapper;

    @Resource
    private ConstDoMapper constDoMapper;

    /*
     * @Description: 申请课程
     * @Method: [courseId, userId]
     * @Return: com.shu.course_backend.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/5 22:14
     */
    @Override
    public Result applyTheCourseByTeacher(Integer courseId, String userId) {
        // 首先查看是否已经在申请中
        String semester = constDoMapper.selectByPrimaryKey("NOW_SEMESTER").getConfigValue();
        ApplicationDoExample applicationDoExample = new ApplicationDoExample();
        applicationDoExample
                .createCriteria()
                .andCourseIdEqualTo(courseId)
                .andTeacherIdEqualTo(userId)
                .andSemseterEqualTo(semester)
                .andStatusNotEqualTo(-1);
        List<ApplicationDo> applicationDoList = applicationDoMapper.selectByExample(applicationDoExample);

        if (!ObjectUtils.isEmpty(applicationDoList)) {
            return Result.success("正在申请中或已申请成功");
        }
        ApplicationDo record = new ApplicationDo();
        record.setTeacherId(userId);
        record.setCourseId(courseId);
        record.setCreateTime(new Date(System.currentTimeMillis()));
        record.setStatus(0);
        record.setSemseter(semester);
        try {
            if (applicationDoMapper.insertSelective(record) == 1) {
                return Result.success("申请成功");
            } else {
                throw new AllException(EmAllException.DATABASE_ERROR, "申请失败");
            }
        } catch (AllException ex) {
            return Result.error(ex);
        }

    }
}
