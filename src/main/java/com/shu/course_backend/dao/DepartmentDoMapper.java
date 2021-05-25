package com.shu.course_backend.dao;

import com.shu.course_backend.model.entity.DepartmentDo;
import com.shu.course_backend.model.entity.DepartmentDoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentDoMapper {
    int countByExample(DepartmentDoExample example);

    int deleteByExample(DepartmentDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DepartmentDo record);

    int insertSelective(DepartmentDo record);

    List<DepartmentDo> selectByExample(DepartmentDoExample example);

    DepartmentDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DepartmentDo record, @Param("example") DepartmentDoExample example);

    int updateByExample(@Param("record") DepartmentDo record, @Param("example") DepartmentDoExample example);

    int updateByPrimaryKeySelective(DepartmentDo record);

    int updateByPrimaryKey(DepartmentDo record);
}