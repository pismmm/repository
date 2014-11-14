package com.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shizhenchao on 2014-9-24.
 */
public class BaseAction extends ActionSupport {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    public BaseAction() {
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();
        session = request.getSession();
    }

    protected void writeJson(String jsonStr) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
