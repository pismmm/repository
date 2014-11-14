package com.ssh.service.impl;

import com.ssh.dao.MenuDao;
import com.ssh.model.Menu;
import com.ssh.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shizhenchao on 2014-9-24.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    public List<Menu> loadMenus(String where) {
        return menuDao.loadMenus(where);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Menu> loadMenusByPid(int pid) {
        List<Menu> menus = menuDao.loadMenusByPid(pid);
        if (menus != null && menus.size() > 0) {
            for (Menu menu : menus) {
                List<Menu> menusTemp = this.loadMenusByPid(menu.getId());
                //isParent
                if (menusTemp != null && menusTemp.size() > 0) {
                    menu.setParent(true);
                } else {
                    menu.setParent(false);
                }
            }
        }
        return menus;
    }

    /**
     * 加载需要处理的tree，增删改查
     *
     * @return
     */
    public List<Menu> loadMenusForDeal() {
        List<Menu> menus = menuDao.loadMenus("");
        //递归，组装ztree的标准类型
        recursiveTreeList(menus);
        return menus;
    }

    /**
     * 重命名节点
     *
     * @param menu
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean rename(Menu menu) {
        return menuDao.rename(menu);
    }

    /**
     * 删除节点
     *
     * @param menu
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean remove(Menu menu) {
        return menuDao.remove(menu);
    }

    /**
     * 添加节点
     *
     * @param menu
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Menu menu) {
        return menuDao.add(menu);
    }

    /**
     * 拖拽操作
     *
     * @param menu
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean dragAndDrop(Menu menu) {
        return menuDao.dragAndDrop(menu);
    }

    public List<Menu> recursiveTreeList(List<Menu> menus) {
        if (menus != null && menus.size() > 0) {
            for (Menu menu : menus) {
                List<Menu> subMenus = menuDao.loadMenusByPid(menu.getId());
                if (subMenus != null && subMenus.size() > 0) {
                    menu.setParent(true);
                }
            }
        }
        return menus;
    }
}
