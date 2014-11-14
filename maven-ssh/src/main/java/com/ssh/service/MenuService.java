package com.ssh.service;

import com.ssh.model.Menu;

import java.util.List;

/**
 * Created by shizhenchao on 2014-9-24.
 */
public interface MenuService {
    List<Menu> loadMenus(String where);

    List<Menu> loadMenusByPid(int pid);

    /**
     * 加载需要处理的tree，增删改查
     *
     * @return
     */
    List<Menu> loadMenusForDeal();

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
     * 添加节点
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
