package com.ssm.mapper.base.menu;


import com.ssm.dto.base.menu.Menu;
import com.ssm.dto.base.menu.MenuVo;

import java.util.List;

public interface AuMenuMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectAll();

    List<MenuVo> selectByMap();

    List<Menu> selectByParentId(int parentId);

}