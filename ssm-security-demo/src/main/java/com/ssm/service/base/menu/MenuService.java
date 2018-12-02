package com.ssm.service.base.menu;

import com.ssm.dto.base.menu.Menu;
import com.ssm.dto.base.menu.MenuVo;

import java.util.List;

/**
 * @author 贾令强
 * @since 2018/5/12 14:49
 */
public interface MenuService {

    List<Menu> getFunctionTree();

    List<MenuVo> getMenus(String parentModuleId, Menu menu);

    List<Menu> getParents();

    int saveMenu(Menu menu, String userName);

    int deleteMenu(Integer[] ids, String userName);

    int updateMenu(Menu menu, String userName);
}
