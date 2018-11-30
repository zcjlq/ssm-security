package com.ssm.service.base.impl.menu;

import com.ssm.dto.base.menu.Menu;
import com.ssm.dto.base.menu.MenuVo;
import com.ssm.mapper.base.menu.AuMenuMapper;
import com.ssm.mapper.base.menu.MenuMapper;
import com.ssm.service.BaseService;
import com.ssm.service.base.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 贾令强
 * @since 2018/5/12 14:50
 */
@Service
public class MenuServiceImpl extends BaseService implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private AuMenuMapper auMenuMapper;

    @Override
    public List<Menu> getFunctionTree() {
        List<Menu> menuList = auMenuMapper.selectAll();
        // 所有一级目录集合
        List<Menu> newMenuList = new ArrayList<>();
        for (Menu menu : menuList) {
            //父ID是0 说明是一级目录
            if (menu.getParentId() == 0) {
                List<Menu> childList = this.getChildren(menu, menuList);
                //如果一级目录子目录不为空
                if (childList != null && childList.size() > 0) {
                    menu.setChildren(childList);
                }
                newMenuList.add(menu);
            }
        }
        return newMenuList;
    }

    @Override
    public List<Menu> getFunctionTree1() {
        // todo 根据权限获取树
        List<Menu> auFunctionTrees = auMenuMapper.selectAll();
        return auFunctionTrees;
    }

    @Override
    public List<MenuVo> getMenus(String parentModule, Menu menu) {
        String text = menu.getText();
        String url = menu.getUrl();
        String operUser = menu.getOperUser();
        if (log.isInfoEnabled()) {
            log.info("查询菜单，参数:父菜单名称[{}],菜单名称[{}],url[{}],操作用户[{}]",
                    parentModule, text, url, operUser);
        }
        List<MenuVo> menuVos = auMenuMapper.selectByMap(parentModule, text, url, operUser);
        return menuVos;
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
    public int saveMenu(Menu menu, String userName) {
        if (log.isInfoEnabled()) {
            log.info("新增菜单参数:{}", menu);
        }
        menu.setCreateTime(new Date());
        menu.setLastUpdate(new Date());
        int i = auMenuMapper.insertSelective(menu);
        if (log.isInfoEnabled()) {
            log.info("新增菜单结果");
        }
        return i;
    }

    @Override
    public int deleteMenu(Integer[] ids, String userName) {
        int i = auMenuMapper.deleteByPrimaryKey(ids);
        if (log.isInfoEnabled()) {
            log.info("删除菜单数量[{}]", i);
        }
        return i;
    }

    @Override
    public int updateMenu(Menu menu, String userName) {
        menu.setLastUpdate(new Date());
//        menu.setOperUser(userName);
        return auMenuMapper.updateByPrimaryKey(menu);
    }

    /**
     * 根据当前目录，递归找到其所有子节点
     *
     * @param p
     * @param menuList
     * @return
     * @date
     * @author
     */
    private List<Menu> getChildren(Menu parentMenu, List<Menu> allMenus) {
        List<Menu> childrenMenus = new ArrayList<>();
        allMenus.forEach(menu -> {
            if (menu.getParentId().equals(parentMenu.getId())) {
                childrenMenus.add(menu);
            }
        });
        return childrenMenus;
    }
}


