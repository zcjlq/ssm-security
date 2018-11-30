package com.ssm.mapper.base.menu;


import com.ssm.dto.base.menu.Menu;
import com.ssm.dto.base.menu.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuMenuMapper {

    int deleteByPrimaryKey(Integer[] ids);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectAll();

    List<MenuVo> selectByMap(@Param("parentName") String parentModule,
                             @Param("text") String text,
                             @Param("url") String url,
                             @Param("operUser") String operUser);

    List<Menu> selectByParentId(int parentId);

}