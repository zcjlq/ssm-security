package com.ssm.service.base.param;

import com.ssm.dto.base.param.Param;

import java.util.List;

/**
 * @author 贾令强
 * @since 2018/4/30 18:23
 */
public interface ParamService {

    List<Param> getProjectName();

    List<Param> list();

    void addFinance(Param paramCfg);

    void delete(String paramIds);

    List<Param> paramDetail(String paramType);

}