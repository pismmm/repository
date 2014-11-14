package com.ssh.service.impl;

import com.ssh.dao.UserDao;
import com.ssh.model.User;
import com.ssh.service.UserService;
import com.ssh.util.PageHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by shizhenchao on 2014-9-13.
 */
@Component
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.findUserByNameAndPass(user);
    }

    /**
     * 用户列表
     *
     * @param user
     * @param pageHelper
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<User> userGrid(User user, PageHelper pageHelper) {
        int startNo = ((pageHelper.getPage() - 1) * pageHelper.getRows());
        int firstResult = startNo <= 0 ? 0 : startNo;
        int maxResults = pageHelper.getRows();
        return userDao.loadUsersByWhere(user, firstResult, maxResults);
    }

    /**
     * 保存或者更新
     *
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(User user) throws Exception {
        userDao.saveOrUpdate(user);
    }

    /**
     * 通过主键list集合删除
     *
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<String> ids) {
        for (String str : ids) {
            User user = new User();
            user.setId(str);
            userDao.delete(user);
        }
    }

    /**
     * 加载男女管理员,返回json字符串
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Map<String, Object>> userMaleAndFemale() {
        Long maleCount = userDao.countMaleUsers();
        Long femaleCount = userDao.countFemaleUsers();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "男");
        map.put("y", maleCount);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "女");
        map2.put("y", femaleCount);
        map2.put("sliced", true);
        map2.put("selected", true);
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        maps.add(map);
        maps.add(map2);
        return maps;
    }
}
