package com.ssm.web.controller.base.menu;

import com.alibaba.fastjson.JSON;
import com.ssm.dto.base.menu.Menu;
import com.ssm.dto.base.menu.MenuVo;
import com.ssm.dto.base.user.User;
import com.ssm.service.base.menu.MenuService;
import com.ssm.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 菜单
 *
 * @author 贾令强
 * @since 2018/5/12 14:48
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    //    @RequestMapping("/tree")
    public ResponseEntity<String> getFunctionTree() {
        List<Menu> functionTree = menuService.getFunctionTree();
        String jsonString = JSON.toJSONString(functionTree);
        return ResponseEntity.status(HttpStatus.OK).body(jsonString);
    }

    @RequestMapping("/tree")
    public ResponseEntity<String> getFunctionTree1() {
        List<Menu> functionTree = menuService.getFunctionTree1();
        String jsonString = JSON.toJSONString(functionTree);
        return ResponseEntity.status(HttpStatus.OK).body(jsonString);
    }

    @RequestMapping("/list")
    public ResponseEntity<String> getMenus() {
        List<MenuVo> functionTree = menuService.getMenus();
        String jsonString = JSON.toJSONString(functionTree);
        return ResponseEntity.status(HttpStatus.OK).body(jsonString);
    }

    @RequestMapping("/parent")
    public ResponseEntity<String> getParents() {
        List<Menu> functionTree = menuService.getParents();
        String jsonString = JSON.toJSONString(functionTree);
        return ResponseEntity.status(HttpStatus.OK).body(jsonString);
    }

    @PostMapping("/parent")
    public ResponseEntity<String> saveMenu(Menu menu) {
        User user = getUser();
        menu.setOperUser("qqq");
        Boolean b = menuService.saveMenu(menu);
//        String jsonString = JSON.toJSONString(functionTree);
//        return ResponseEntity.status(HttpStatus.OK).body(jsonString);
        return null;
    }
}
