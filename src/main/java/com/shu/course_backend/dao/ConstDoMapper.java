package com.shu.course_backend.dao;

import com.shu.course_backend.model.entity.ConstDo;
import com.shu.course_backend.model.entity.ConstDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConstDoMapper {
    int countByExample(ConstDoExample example);

    int deleteByExample(ConstDoExample example);

    int deleteByPrimaryKey(String key);

    int insert(ConstDo record);

    int insertSelective(ConstDo record);

    List<ConstDo> selectByExample(ConstDoExample example);

    ConstDo selectByPrimaryKey(String key);

    int updateByExampleSelective(@Param("record") ConstDo record, @Param("example") ConstDoExample example);

    int updateByExample(@Param("record") ConstDo record, @Param("example") ConstDoExample example);

    int updateByPrimaryKeySelective(ConstDo record);

    int updateByPrimaryKey(ConstDo record);
}