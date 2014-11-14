package com.ssh.dao;

import com.ssh.model.User;
import com.ssh.util.PageHelper;

import java.util.List;

/**
 * Created by shizhenchao on 2014-9-13.
 */
public interface UserDao {
    User findUserByNameAndPass(User user);

    List<User> loadUsersByWhere(User user, int firstResult, int maxResults);

    /**
     * 保存或者更新
     *
     * @param user
     */
    void saveOrUpdate(User user);

    void delete(User user);

    Long countMaleUsers();

    Long countFemaleUsers();
}
