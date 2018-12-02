package com.ssm.service.base.param;

import com.ssm.dto.base.param.Param;
import com.ssm.mapper.base.param.ParamMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 贾令强
 * @since 2018/4/30 18:23
 */
@Service
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamMapper paramMapper;


    @Override
    public List<Param> getProjectName() {
        return paramMapper.getProjectName();
    }

    @Override
    public List<Param> list() {
        return paramMapper.list();
    }

    @Override
    public void addFinance(Param paramCfg) {
        paramMapper.insert(paramCfg);
    }

    @Override
    public void delete(String financeIds) {
        List<String> itemIdList = Arrays.asList(financeIds.split(","));
        if (!CollectionUtils.isEmpty(itemIdList)) {
            paramMapper.deletes(itemIdList);
        }
    }

    @Override
    public List<Param> paramDetail(String paramType) {
        return paramMapper.paramDetail(paramType);
    }


}
