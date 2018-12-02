package com.ssm.web.controller.base.param;

import com.ssm.dto.base.param.Param;
import com.ssm.service.base.param.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 贾令强
 * @since 2018/4/30 18:21
 */
@RestController
@RequestMapping("/param")
public class ParamController {

    @Autowired
    private ParamService paramService;

    @RequestMapping("/project")
    public List<Param> getProjectName() {
        List<Param> paramCfgList = paramService.getProjectName();

        return paramCfgList;
    }

    @RequestMapping("/list")
    public List<Param> list() {
        List<Param> paramCfgList = paramService.list();
        return paramCfgList;
    }

    @RequestMapping("/paramDetail")
    public List<Param> paramDetail(String paramType) {
        List<Param> paramCfgList = paramService.paramDetail(paramType);
        return paramCfgList;
    }

    @RequestMapping("/add")
    public ResponseEntity<String> addItem(Param paramCfg) {
        paramService.addFinance(paramCfg);
        // todo 乱码
        String json = "success";
        ResponseEntity<String> re = new ResponseEntity<>(json, HttpStatus.OK);
        return re;
    }

    @RequestMapping("/delete")
    public String delete(String paramIds) {
        paramService.delete(paramIds);
        return "ok";
    }
}