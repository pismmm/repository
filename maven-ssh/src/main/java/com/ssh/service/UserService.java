package com.ssh.service;

import com.ssh.model.User;
import com.ssh.util.PageHelper;

import java.util.List;
import java.util.Map;


/**
 * Created by shizhenchao on 2014-9-13.
 */
public interface UserService {

    User login(User user);

    /**
     * 用户列表
     *
     * @param user
     * @param pageHelper
     * @return
     */
    List<User> userGrid(User user, PageHelper pageHelper);

    /**
     * 保存或者更新
     *
     * @param user
     */
    void saveOrUpdate(User user) throws Exception;

    /**
     * 通过主键list集合删除
     *
     * @param list
     */
    void delete(List<String> list);

    /**
     * 加载男女管理员
     *
     * @return
     */
    List<Map<String, Object>> userMaleAndFemale();
}
