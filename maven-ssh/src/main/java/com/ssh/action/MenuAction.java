package com.ssh.action;

import com.google.gson.Gson;
import com.ssh.model.Menu;
import com.ssh.service.MenuService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by shizhenchao on 2014-9-24.
 */
@ParentPackage("struts-default")
@Namespace(value = "/menus")
public class MenuAction extends BaseAction {
    private List<Menu> menus;
    private Menu menu;
    private int id;
    @Resource
    private MenuService menuService;

    /**
     * 加载左侧tree
     */
    @Action(value = "loadMenuTree")
    public void loadMenus() {
        menus = menuService.loadMenusByPid(id);
        String json = new Gson().toJson(menus).toString();
        super.writeJson(json);
    }

    /**
     * 显示tree
     */
    @Action(value = "menusTreeForDeal")
    public void menusTreeForDeal() {
        menus = menuService.loadMenusForDeal();
        String json = new Gson().toJson(menus).toString();
        super.writeJson(json);
    }

    /**
     * 重命名
     */
    @Action(value = "rename")
    public void rename() {
        boolean t = menuService.rename(menu);
        if (t) {
            super.writeJson("yes");
        } else {
            super.writeJson("no");
        }
    }

    /**
     * 删除
     */
    @Action(value = "remove")
    public void remove() {
        boolean t = menuService.remove(menu);
        if (t) {
            super.writeJson("yes");
        } else {
            super.writeJson("no");
        }
    }

    /**
     * 添加
     */
    @Action(value = "add")
    public void add() {
        boolean t = menuService.add(menu);
        if (t) {
            super.writeJson("yes");
        } else {
            super.writeJson("no");
        }
    }

    /**
     * 拖拽
     */
    @Action(value = "dragAndDrop")
    public void dragAndDrop() {
        boolean t = menuService.dragAndDrop(menu);
        if (t) {
            super.writeJson("yes");
        } else {
            super.writeJson("no");
        }
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
