package com.ssh.dao.impl;

import com.ssh.dao.UserDao;
import com.ssh.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shizhenchao on 2014-9-13.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Resource
    HibernateTemplate hibernateTemplate;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User findUserByNameAndPass(User user) {
        List<User> users;
        try {
            users = (List) hibernateTemplate.findByNamedParam("from User u where u.username=:username and u.password=:password", new String[]{"username", "password"}, new Object[]{user.getUsername(), user.getPassword()});
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public List<User> loadUsersByWhere(User user, final int firstResult, final int maxResults) {
        List<User> users = hibernateTemplate.execute(new HibernateCallback<List<User>>() {
            @Override
            public List<User> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("from User");
                List<User> list = query.list();
                return list;
            }
        });
        return users;
    }

    /**
     * 保存或者更新
     *
     * @param user
     */
    public void saveOrUpdate(User user) {
        hibernateTemplate.saveOrUpdate(user);
    }

    public void delete(User user) {
        hibernateTemplate.delete(user);
    }

    public Long countMaleUsers() {
        final String hql = "select count(*) from User u where u.sex = '0'";
        Long resultTotal = (Long) hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                return query.uniqueResult();
            }
        });
        return resultTotal;
    }

    public Long countFemaleUsers() {
        final String hql = "select count(*) from User u where u.sex <> '0'";
        Long resultTotal = (Long) hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                return query.uniqueResult();
            }
        });
        return resultTotal;
    }
}
