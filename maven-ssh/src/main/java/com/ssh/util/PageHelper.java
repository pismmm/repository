package com.ssh.util;

import java.io.Serializable;

/**
 * Created by shizhenchao on 2014-9-25.
 */
public class PageHelper implements Serializable {
    private int page;
    private int rows;
    private int sidx;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSidx() {
        return sidx;
    }

    public void setSidx(int sidx) {
        this.sidx = sidx;
    }
}
