package com.ssm.mapper.base.log;

import com.ssm.dto.base.log.OperLog;

import java.util.List;
import java.util.Map;

public interface OperLogMapper {

    int insert(OperLog record);

    int insertSelective(OperLog record);

    List<OperLog> queryAll(Map<String, Object> paramMap);
}