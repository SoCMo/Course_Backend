package com.shu.course_backend.dao;

import com.shu.course_backend.model.entity.CourseTimeDo;
import com.shu.course_backend.model.entity.CourseTimeDoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTimeDoMapper {
    int countByExample(CourseTimeDoExample example);

    int deleteByExample(CourseTimeDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseTimeDo record);

    int insertSelective(CourseTimeDo record);

    List<CourseTimeDo> selectByExample(CourseTimeDoExample example);

    CourseTimeDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseTimeDo record, @Param("example") CourseTimeDoExample example);

    int updateByExample(@Param("record") CourseTimeDo record, @Param("example") CourseTimeDoExample example);

    int updateByPrimaryKeySelective(CourseTimeDo record);

    int updateByPrimaryKey(CourseTimeDo record);
}