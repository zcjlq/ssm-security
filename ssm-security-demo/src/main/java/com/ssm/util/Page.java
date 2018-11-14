package com.ssm.util;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 贾令强
 * @since 2018/10/24 4:07 PM
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 7119135419721732791L;

    private Long total;
    private Long page;
    private List<T> rows;

    public Page() {
    }

    public Page(List<T> rows) {
        this.rows = rows;
        PageInfo<T> pageInfo = new PageInfo<>(rows);
        this.total = pageInfo.getTotal();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
