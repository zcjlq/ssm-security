package com.ssm.web.controller.base.log;

import com.ssm.dto.base.log.OperLog;
import com.ssm.service.base.log.OperLogService;
import com.ssm.util.Page;
import com.ssm.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 贾令强
 * @since 2018/10/22 3:59 PM
 */
@Controller
@RequestMapping("/log")
public class OperLogController extends BaseController {

    @Autowired
    private OperLogService operLogService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<OperLog> queryLog(WebRequest request) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startDate", request.getParameter("startDate"));
        paramMap.put("endDate", request.getParameter("endDate"));
        paramMap.put("logType", request.getParameter("logType"));
        paramMap.put("ip", request.getParameter("ip"));
        paramMap.put("moduleName", request.getParameter("moduleName"));
        paramMap.put("operUser", request.getParameter("operUser"));
        String pageNum = request.getParameter("page");
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", request.getParameter("rows"));
        log.info("queryLog传入参数为：{},", paramMap.toString());
        return operLogService.queryLog(paramMap);
    }
}
