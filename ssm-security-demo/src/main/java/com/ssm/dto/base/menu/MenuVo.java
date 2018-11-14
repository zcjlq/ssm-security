package com.ssm.dto.base.menu;

/**
 * @author 贾令强
 * @since 2018/11/4 6:57 PM
 */
public class MenuVo extends Menu {

    private static final long serialVersionUID = -5299555141868589704L;

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
