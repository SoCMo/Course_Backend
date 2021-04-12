package com.shu.course_backend.dao;

import com.shu.course_backend.model.entity.OpenDo;
import com.shu.course_backend.model.entity.OpenDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpenDoMapper {
    int countByExample(OpenDoExample example);

    int deleteByExample(OpenDoExample example);

    int deleteByPrimaryKey(Integer openId);

    int insert(OpenDo record);

    int insertSelective(OpenDo record);

    List<OpenDo> selectByExampleWithBLOBs(OpenDoExample example);

    List<OpenDo> selectByExample(OpenDoExample example);

    OpenDo selectByPrimaryKey(Integer openId);

    int updateByExampleSelective(@Param("record") OpenDo record, @Param("example") OpenDoExample example);

    int updateByExampleWithBLOBs(@Param("record") OpenDo record, @Param("example") OpenDoExample example);

    int updateByExample(@Param("record") OpenDo record, @Param("example") OpenDoExample example);

    int updateByPrimaryKeySelective(OpenDo record);

    int updateByPrimaryKeyWithBLOBs(OpenDo record);

    int updateByPrimaryKey(OpenDo record);
}