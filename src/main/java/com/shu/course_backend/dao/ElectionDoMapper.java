package com.shu.course_backend.dao;

import com.shu.course_backend.model.entity.ElectionDo;
import com.shu.course_backend.model.entity.ElectionDoExample;
import com.shu.course_backend.model.response.GradeResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ElectionDoMapper {
    int countByExample(ElectionDoExample example);

    int deleteByExample(ElectionDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ElectionDo record);

    int insertSelective(ElectionDo record);

    List<ElectionDo> selectByExample(ElectionDoExample example);

    ElectionDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ElectionDo record, @Param("example") ElectionDoExample example);

    int updateByExample(@Param("record") ElectionDo record, @Param("example") ElectionDoExample example);

    int updateByPrimaryKeySelective(ElectionDo record);

    int updateByPrimaryKey(ElectionDo record);

    List<GradeResponse> selectNameAndGrade(Integer openid);

    Double getGradePoint(@Param("grade") Double grade);
}