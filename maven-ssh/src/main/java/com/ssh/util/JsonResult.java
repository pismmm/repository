package com.ssh.util;

import java.io.Serializable;

/**
 * Created by shizhenchao on 2014-9-13.
 */
public class JsonResult implements Serializable {
    private boolean flag;
    private String msg;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
