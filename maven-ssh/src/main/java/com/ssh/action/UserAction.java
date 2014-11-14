package com.ssh.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.model.User;
import com.ssh.service.UserService;
import com.ssh.util.CommonUtils;
import com.ssh.util.PageHelper;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shizhenchao on 2014-9-25.
 */
@ParentPackage("struts-default")
@Namespace("/user")
public class UserAction extends BaseAction implements ModelDriven<User> {
    private PageHelper pageHelper;
    @Resource
    private UserService userService;

    private List<User> users;

    private User user = new User();

    private int page;
    private int rows;
    private String oper;//区别操作，add,edit,search

    @Action(value = "userGridPage", results = {@Result(name = "success", location = "/views/user/user-grid.jsp")})
    public String userGridPage() {
        return "success";
    }

    /**
     * users数据
     */
    @Action(value = "userGrid")
    public void userGrid() {
        PageHelper pageHelper1 = new PageHelper();
        pageHelper1.setPage(page);
        pageHelper1.setRows(rows);
        users = userService.userGrid(user, pageHelper1);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", users);
        map.put("page", page);
        map.put("total", 2);
        map.put("records", 13);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        super.writeJson(gson.toJson(map));
    }

    @Action(value = "operate")
    public void saveOrUpdate() throws Exception {
        //通过oper的值判断执行什么样的操作
        if (oper != null) {
            if (oper.equals("add")) {
                try {
                    //执行添加操作
                    userService.saveOrUpdate(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    super.writeJson("no");
                }
                super.writeJson("yes");
            } else if (oper.equals("edit")) {
                //执行编辑操作
                userService.saveOrUpdate(user);
            } else if (oper.equals("del")) {
                //执行删除操作
                String id = user.getId();
                if (id.contains(",")) {
                    List<String> list = CommonUtils.splitByComma2StringList(id);
                    userService.delete(list);
                }
            }
        }
    }

    public List<User> getUsers() {
        return users;
    }

    /**
     * 男女结构图
     */
    @Action(value = "userMaleAndFemale")
    public void userMaleAndFemale() {
        List<Map<String, Object>> list = userService.userMaleAndFemale();
        super.writeJson(new Gson().toJson(list));
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

    public void setPageHelper(PageHelper pageHelper) {
        this.pageHelper = pageHelper;
    }

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

    public User getModel() {
        return user;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}
