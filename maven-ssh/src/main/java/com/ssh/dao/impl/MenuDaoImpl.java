package com.ssh.dao.impl;

import com.ssh.dao.MenuDao;
import com.ssh.model.Menu;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shizhenchao on 2014-9-24.
 */
@Repository
public class MenuDaoImpl implements MenuDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    public List<Menu> loadMenus(String where) {
        return (List<Menu>) hibernateTemplate.find("from Menu");
    }

    public List<Menu> loadMenusByPid(int pid) {
        List<Menu> menus = (List<Menu>) hibernateTemplate.find("from Menu where pid = ?", pid);
        return menus;
    }

    /**
     * 重命名节点
     *
     * @param menu
     * @return
     */
    public boolean rename(Menu menu) {
        try {
            int id = menu.getId();
            String name = menu.getName();
            Menu menu1 = hibernateTemplate.get(Menu.class, id);
            menu1.setName(name);
            hibernateTemplate.update(menu1);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除节点
     *
     * @param menu
     * @return
     */
    public boolean remove(Menu menu) {
        try {
            hibernateTemplate.delete(menu);
            List<Menu> list = this.loadMenusByPid(menu.getId());
            //级联删除
            if (list != null && list.size() > 0) {
                hibernateTemplate.deleteAll(list);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    public boolean add(Menu menu) {
        try {
            hibernateTemplate.save(menu);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 拖拽操作
     *
     * @param menu
     * @return
     */
    public boolean dragAndDrop(Menu menu) {
        try {
            int id = menu.getId();
            Menu menu1 = hibernateTemplate.get(Menu.class, id);
            menu1.setPid(menu.getTargetNodeId());
            hibernateTemplate.update(menu1);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
