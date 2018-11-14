package com.ssm.service.base.impl.menu;

import com.ssm.dto.base.menu.Menu;
import com.ssm.dto.base.menu.MenuVo;
import com.ssm.mapper.base.menu.AuMenuMapper;
import com.ssm.mapper.base.menu.MenuMapper;
import com.ssm.service.base.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 贾令强
 * @since 2018/5/12 14:50
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private AuMenuMapper auMenuMapper;

    @Override
    public List<Menu> getFunctionTree() {
        // 获得用户权限
        List<Menu> functionTreeParent = menuMapper.getFunctionTreeParent();
        // 所有一级目录集合
        List<Menu> functionTrees = new ArrayList<>();
        for (Menu functionTree : functionTreeParent) {
            //父ID是0 说明是一级目录
            if (functionTree.getParentId() == 0) {
                Menu parent = new Menu();
                parent.setId(functionTree.getId());
                parent.setState("open");
                parent.setText(functionTree.getText());
                parent.setIconcls("icon-man");
                Map<String, String> attrMap = new HashMap<>();
                attrMap.put("url", functionTree.getUrl());
//                parent.setAttributes(attrMap);
                List<Menu> childList = getChildren(functionTree, functionTreeParent);
                //如果一级目录子目录不为空才展示出来
                if (childList != null && childList.size() > 0) {
                    parent.setChildren(childList);
                    functionTrees.add(parent);
                }
            }
        }
        return functionTreeParent;
    }

    @Override
    public List<Menu> getFunctionTree1() {
        // todo 根据权限获取树
        List<Menu> auFunctionTrees = auMenuMapper.selectAll();
        return auFunctionTrees;
    }

    @Override
    public List<MenuVo> getMenus() {
        return auMenuMapper.selectByMap();
    }

    @Override
    public List<Menu> getParents() {

        List<Menu> menus = auMenuMapper.selectByParentId(0);
        Menu menu = new Menu();
        menu.setId(0);
        menu.setText("一级菜单");
        menus.add(0, menu);
        return menus;
    }

    @Override
    public Boolean saveMenu(Menu menu) {
        menu.setCreateTime(new Date());
        menu.setLastUpdate(new Date());
        return auMenuMapper.insertSelective(menu) > 0;
    }

    /**
     * 根据当前目录，递归找到其所有子节点
     *
     * @param p
     * @param functionTreeParent
     * @return
     * @date
     * @author
     */
    private List<Menu> getChildren(Menu p, List<Menu> functionTreeParent) {
        List<Menu> mList = new ArrayList<>();
        // 在权限集合中找到,当前权限菜单的子节点
        for (Menu parent : functionTreeParent) {
            // 如果某个菜单的父ID为当前菜单ID，那么就把它作为当前菜单的child
            if ((parent.getParentId() + 1) == (p.getId() + 1)) {
                //封装菜单对象
                Menu tm = new Menu();
                tm.setId(parent.getId());
                tm.setState("open");
                tm.setText(parent.getText());
                tm.setIconcls("icon-lock");
                Map<String, String> attrMap = new HashMap<>();
                attrMap.put("url", parent.getUrl());
//                tm.setAttributes(attrMap);
                tm.setChildren(getChildren(parent, functionTreeParent));
                mList.add(tm);
            }
        }
        return mList;
    }
}


