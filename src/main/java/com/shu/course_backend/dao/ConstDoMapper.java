package com.shu.course_backend.dao;

import com.shu.course_backend.model.entity.ConstDo;
import com.shu.course_backend.model.entity.ConstDoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConstDoMapper {
    int countByExample(ConstDoExample example);

    int deleteByExample(ConstDoExample example);

    int deleteByPrimaryKey(String configKey);

    int insert(ConstDo record);

    int insertSelective(ConstDo record);

    List<ConstDo> selectByExample(ConstDoExample example);

    ConstDo selectByPrimaryKey(String configKey);

    int updateByExampleSelective(@Param("record") ConstDo record, @Param("example") ConstDoExample example);

    int updateByExample(@Param("record") ConstDo record, @Param("example") ConstDoExample example);

    int updateByPrimaryKeySelective(ConstDo record);

    int updateByPrimaryKey(ConstDo record);
}