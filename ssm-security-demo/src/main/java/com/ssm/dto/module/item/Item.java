package com.ssm.dto.module.item;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 贾令强
 * @since 2018/4/22 23:31
 */
public class Item implements Serializable {

    private static final long serialVersionUID = -2954450866757024715L;

    private Long tid;

    private String itemId;

    private String productId;

    private BigDecimal listPrice;

    private BigDecimal unitCost;

    private String attr1;

    private String status1;

    private Date lastUpdate;

    public Item() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "tid=" + tid +
                ", itemId='" + itemId + '\'' +
                ", productId='" + productId + '\'' +
                ", listPrice=" + listPrice +
                ", unitCost=" + unitCost +
                ", attr1='" + attr1 + '\'' +
                ", status1='" + status1 + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
