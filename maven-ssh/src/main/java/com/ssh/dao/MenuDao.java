package com.ssh.dao;

import com.ssh.model.Menu;

import java.util.List;

/**
 * Created by shizhenchao on 2014-9-24.
 */
public interface MenuDao {
    List<Menu> loadMenus(String where);

    List<Menu> loadMenusByPid(int pid);

    /**
     * 重命名节点
     *
     * @param menu
     * @return
     */
    boolean rename(Menu menu);

    /**
     * 删除节点
     *
     * @param menu
     * @return
     */
    boolean remove(Menu menu);

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    boolean add(Menu menu);

    /**
     * 拖拽操作
     *
     * @param menu
     * @return
     */
    boolean dragAndDrop(Menu menu);
}
