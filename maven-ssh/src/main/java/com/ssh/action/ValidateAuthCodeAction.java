package com.ssh.action;

import com.ssh.util.JsonResult;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

/**
 * Created by shizhenchao on 2014-9-13.
 */
@ParentPackage("json-default")
@Result(name = "json", type = "json")
public class ValidateAuthCodeAction {
    private String authCode;
    private JsonResult jsonResult = new JsonResult();

    @Action(value = "validateAuthCode")
    public String validateAuthCode() {
        try {
            //获取session中的验证码
            String anthCodeFromSession = (String) ServletActionContext.getRequest().getSession().getAttribute("authCode");
            if (authCode.equalsIgnoreCase(anthCodeFromSession)) {
                jsonResult.setFlag(true);
            } else {
                jsonResult.setFlag(false);
                jsonResult.setMsg("用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setFlag(false);
            jsonResult.setMsg("用户名或密码错误");
        }
        return "json";
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @JSON
    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }
}
