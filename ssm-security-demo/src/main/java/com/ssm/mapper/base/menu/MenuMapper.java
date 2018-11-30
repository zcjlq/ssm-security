package com.ssm.mapper.base.menu;

import com.ssm.dto.base.menu.Menu;

import java.util.List;

public interface MenuMapper {

    int insert(Menu record);

    List<Menu> getFunctionTreeParent();

    int deleteMenu(Integer[] ids);
}