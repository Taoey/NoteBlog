package com.tao.dao;

import com.tao.pojo.Note2tag;
import com.tao.pojo.Note2tagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Note2tagMapper {
    long countByExample(Note2tagExample example);

    int deleteByExample(Note2tagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Note2tag record);

    int insertSelective(Note2tag record);

    List<Note2tag> selectByExample(Note2tagExample example);

    Note2tag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Note2tag record, @Param("example") Note2tagExample example);

    int updateByExample(@Param("record") Note2tag record, @Param("example") Note2tagExample example);

    int updateByPrimaryKeySelective(Note2tag record);

    int updateByPrimaryKey(Note2tag record);
}