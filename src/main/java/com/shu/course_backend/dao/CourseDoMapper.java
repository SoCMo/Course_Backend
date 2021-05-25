package com.shu.course_backend.dao;

import com.shu.course_backend.model.entity.CourseDo;
import com.shu.course_backend.model.entity.CourseDoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDoMapper {
    int countByExample(CourseDoExample example);

    int deleteByExample(CourseDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseDo record);

    int insertSelective(CourseDo record);

    List<CourseDo> selectByExample(CourseDoExample example);

    CourseDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseDo record, @Param("example") CourseDoExample example);

    int updateByExample(@Param("record") CourseDo record, @Param("example") CourseDoExample example);

    int updateByPrimaryKeySelective(CourseDo record);

    int updateByPrimaryKey(CourseDo record);

    List<CourseDo> selectCourseWhichHaveTime();
}