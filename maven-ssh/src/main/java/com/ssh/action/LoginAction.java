package com.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.model.User;
import com.ssh.service.UserService;
import com.ssh.util.JsonResult;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import javax.annotation.Resource;

/**
 * Created by shizhenchao on 2014-9-13.
 */
@ParentPackage("json-default")
public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = -2266695172069461659L;
    @Resource
    private UserService userService;
    //private User user;
    private String username;
    private String password;
    private JsonResult jsonResult = new JsonResult();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    @Action(value = "login", results = {
            @Result(name = "json", type = "json")})
    public String login() {
        User userFromPage = new User();
        userFromPage.setPassword(password);
        userFromPage.setUsername(username);
        User userFromDb = null;
        try {
            userFromDb = userService.login(userFromPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userFromDb != null) {
            jsonResult.setFlag(true);
            jsonResult.setMsg("");
            ServletActionContext.getRequest().getSession().setAttribute("userInfo", userFromDb);
        }
        return "json";
    }

    @JSON
    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }
}
