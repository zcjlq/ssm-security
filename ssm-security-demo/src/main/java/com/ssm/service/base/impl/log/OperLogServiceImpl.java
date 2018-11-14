package com.ssm.service.base.impl.log;

import com.ssm.dto.base.log.OperLog;
import com.ssm.mapper.base.log.OperLogMapper;
import com.ssm.service.base.log.OperLogService;
import com.ssm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 贾令强
 * @since 2018/10/22 4:03 PM
 */
@Service
public class OperLogServiceImpl implements OperLogService {
    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public int saveLog(OperLog operLog) {
        return operLogMapper.insertSelective(operLog);
    }

    @Override
    public Page<OperLog> queryLog(Map<String, Object> paramMap) {
        List<OperLog> operLogs = operLogMapper.queryAll(paramMap);
        Page<OperLog> page = new Page<>(operLogs);
        page.setPage(Long.valueOf(paramMap.get("pageNum").toString()));
        return page;
    }

}
