package com.ssm.service.base.log;


import com.ssm.dto.base.log.OperLog;
import com.ssm.util.Page;

import java.util.Map;

/**
 * @author 贾令强
 * @since 2018/10/22 4:02 PM
 */
public interface OperLogService {
    /**
     * 新增日志
     *
     * @param operLog
     * @return
     */
    int saveLog(OperLog operLog);

    Page<OperLog> queryLog(Map<String, Object> paramMap);
}
