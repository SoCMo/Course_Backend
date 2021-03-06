package com.shu.course_backend.dao;

import com.shu.course_backend.model.entity.OpenDo;
import com.shu.course_backend.model.entity.OpenDoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpenDoMapper {
    int countByExample(OpenDoExample example);

    int deleteByExample(OpenDoExample example);

    int deleteByPrimaryKey(Integer openId);

    int insert(OpenDo record);

    int insertSelective(OpenDo record);

    List<OpenDo> selectByExample(OpenDoExample example);

    OpenDo selectByPrimaryKey(Integer openId);

    int updateByExampleSelective(@Param("record") OpenDo record, @Param("example") OpenDoExample example);

    int updateByExample(@Param("record") OpenDo record, @Param("example") OpenDoExample example);

    int updateByPrimaryKeySelective(OpenDo record);

    int updateByPrimaryKey(OpenDo record);
}