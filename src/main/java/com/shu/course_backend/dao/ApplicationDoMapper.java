package com.shu.course_backend.dao;

import com.shu.course_backend.model.entity.ApplicationDo;
import com.shu.course_backend.model.entity.ApplicationDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplicationDoMapper {
    int countByExample(ApplicationDoExample example);

    int deleteByExample(ApplicationDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApplicationDo record);

    int insertSelective(ApplicationDo record);

    List<ApplicationDo> selectByExample(ApplicationDoExample example);

    ApplicationDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApplicationDo record, @Param("example") ApplicationDoExample example);

    int updateByExample(@Param("record") ApplicationDo record, @Param("example") ApplicationDoExample example);

    int updateByPrimaryKeySelective(ApplicationDo record);

    int updateByPrimaryKey(ApplicationDo record);
}