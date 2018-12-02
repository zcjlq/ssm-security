package com.ssm.service.base.menu;

import com.github.pagehelper.PageHelper;
import com.ssm.dto.base.menu.Menu;
import com.ssm.dto.base.menu.MenuVo;
import com.ssm.mapper.base.menu.AuMenuMapper;
import com.ssm.service.BaseService;
import org.apache.commons.lang.StringUtils;
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
    public List<MenuVo> getMenus(String parentModuleId, Menu menu) {
        log.info("查询菜单列表，参数为：parentModuleId[{}],菜单实体：{}", parentModuleId, menu.toString());
        String text = menu.getText();
        String url = menu.getUrl();
        String operUser = menu.getOperUser();
        String sort = menu.getSort();
        if (StringUtils.isNotBlank(sort)) {
            String[] sorts = sort.split(",");
            String order = menu.getOrder();
            String[] orders = order.split(",");
            StringBuilder sq = new StringBuilder("order by parent.id,t.");
            for (int i = 0; i < sorts.length; i++) {
                String sortColumn = sorts[i];
                switch (sortColumn) {
                    case "createUser":
                        sortColumn = "create_user";
                        break;
                    case "createTime":
                        sortColumn = "create_time";
                        break;
                    case "operUser":
                        sortColumn = "oper_user";
                        break;
                    case "lastUpdate":
                        sortColumn = "last_update";
                        break;
                    default:
                }
                sq.append(sortColumn).append(" ").append(orders[i]).append(",");
            }
            sq.deleteCharAt(sq.length() - 1);
            menu.setSort(sq.toString());
        }
        // text, url, operUser
        PageHelper.startPage(menu.getPage(), menu.getRows());
        List<MenuVo> menuVos = auMenuMapper.selectByMap(parentModuleId, menu);
        log.info("查询到菜单数量为：[{}]", menuVos.size());
        return menuVos;
    }

    @Override
    public List<Menu> getParents() {
        log.info("获取有效状态一级菜单");
        List<Menu> menus = auMenuMapper.selectByParentId(0);
        Menu menu = new Menu();
        menu.setText("一级菜单");
        menu.setId(0);
        menus.add(0, menu);
        log.info("获取有效状态一级菜单,数量为：[{}]", menus.size());
        return menus;
    }

    @Override
    public int saveMenu(Menu menu, String userName) {
        if (log.isInfoEnabled()) {
            log.info("新增菜单参数:{}", menu);
        }
        menu.setOperUser(userName);
        menu.setCreateUser(userName);
        menu.setCreateTime(new Date());
        menu.setLastUpdate(new Date());
        int i = auMenuMapper.insertSelective(menu);
        if (log.isInfoEnabled()) {
            log.info("新增菜单结果,影响行数[{}]", i);
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
        menu.setOperUser(userName);
//        menu.setOperUser(userName);
        return auMenuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 根据当前目录，递归找到其所有子节点
     *
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


