package com.ssm.dto.base.menu;

import com.ssm.util.BaseBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Menu extends BaseBean implements Serializable {

    private static final long serialVersionUID = -8714013643214148148L;

    private Integer id;

    // 要显示的节点文本
    private String text;

    // 节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们
    private String state;

    // 指示节点是否被选中
    private String checked;

    // 给一个节点添加的自定义属性
    private String attributes;

    // 图标
    private String iconCls;

    // url
    private String url;

    // Close 为关闭，空为打开
    private String isExpand;

    // 父节点id
    private Integer parentId;

    private String createUser;

    private Date createTime;

    private String operUser;

    private Date lastUpdate;

    private List<Menu> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsExpand() {
        return isExpand;
    }

    public void setIsExpand(String isExpand) {
        this.isExpand = isExpand;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", state='" + state + '\'' +
                ", checked='" + checked + '\'' +
                ", attributes='" + attributes + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", url='" + url + '\'' +
                ", isExpand='" + isExpand + '\'' +
                ", parentId=" + parentId +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", operUser='" + operUser + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", children=" + children +
                '}';
    }
}