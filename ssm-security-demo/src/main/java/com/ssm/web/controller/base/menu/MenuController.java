package com.ssm.web.controller.base.menu;

import com.alibaba.fastjson.JSON;
import com.ssm.dto.base.menu.Menu;
import com.ssm.dto.base.menu.MenuVo;
import com.ssm.service.base.menu.MenuService;
import com.ssm.util.Constant;
import com.ssm.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
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
    private static final String MENU_NAME = "菜单管理";

    @RequestMapping("/tree1")
    public ResponseEntity<String> getFunctionTree() {
        List<Menu> functionTree = menuService.getFunctionTree();
        String jsonString = JSON.toJSONString(functionTree);
        return ResponseEntity.status(HttpStatus.OK).body(jsonString);
    }

    /**
     * sort: text
     * order: asc
     * ttr
     *
     * @param parentModuleId 父菜单名称
     * @param menu           菜单名称，url，操作用户
     * @return
     */
    @RequestMapping("/list")
    public ResponseEntity<String> getMenus(String parentModuleId, Menu menu) {
        long start = System.currentTimeMillis();
        List<MenuVo> menus = menuService.getMenus(parentModuleId, menu);
        String jsonString = JSON.toJSONString(menus);
        super.addLog(start, MENU_NAME, Constant.SEARCH, "查询到条数" + menus.size());
        return ResponseEntity.status(HttpStatus.OK).body(jsonString);
    }

    /**
     * 初始化父菜单下拉框
     *
     * @return
     */
    @RequestMapping("/parent")
    public ResponseEntity<String> getParents() {
        List<Menu> parentMenus = menuService.getParents();
        String jsonString = JSON.toJSONString(parentMenus);
        return ResponseEntity.status(HttpStatus.OK).body(jsonString);
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @PostMapping
    public ResponseEntity<String> addMenu(Menu menu) {
        long start = System.currentTimeMillis();
        log.info("新增菜单，接收到参数为:{}", menu.toString());
        int i = menuService.saveMenu(menu, super.getUserName());
        String msg = i > 0 ? Constant.ADD_SUCCESS : Constant.ADD_FAILURE;
        super.addLog(start, MENU_NAME, Constant.ADD, msg);
        return ResponseEntity.ok(msg);
    }

    /**
     * 删除菜单
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity<String> deleteMenus(Integer[] ids) {
        long start = System.currentTimeMillis();
        log.info("接收到的id是{}", Arrays.toString(ids));
        log.info(Arrays.toString(ids));
        int i = menuService.deleteMenu(ids, super.getUserName());
        String msg = i > 0 ? Constant.DELETE_SUCCESS : Constant.DELETE_FAILURE;
        super.addLog(start, MENU_NAME, Constant.DELETE, msg + ",ids:" + Arrays.toString(ids));
        return ResponseEntity.ok(msg);
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<String> updateMenu(Menu menu) {
        long start = System.currentTimeMillis();
        log.info("修改菜单，接收到参数为:[{}]", menu.toString());
        int i = menuService.updateMenu(menu, super.getUserName());
        String msg = i > 0 ? Constant.UPDATE_SUCCESS : Constant.UPDATE_FAILURE;
        super.addLog(start, MENU_NAME, Constant.UPDATE, msg + ",id:" + menu.getId());
        return ResponseEntity.ok(msg);
    }
}
