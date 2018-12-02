package com.ssm.mapper.base.param;


import com.ssm.dto.base.param.Param;

import java.util.List;

public interface ParamMapper {

    int deleteByPrimaryKey(Integer tid);

    int insert(Param record);

    int insertSelective(Param record);

    Param selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Param record);

    int updateByPrimaryKey(Param record);

    List<Param> getProjectName();

    void deletes(List<String> itemIdList);

    List<Param> list();

    List<Param> paramDetail(String paramType);

}