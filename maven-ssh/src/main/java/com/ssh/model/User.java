package com.ssh.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by shizhenchao on 2014-9-13.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    /**
     * 用户id
     */
    @Id
    @Column(name = "id", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "username", length = 50)
    private String username;

    /**
     * 用户登录密码
     */
    @Column(name = "password", length = 50)
    private String password;

    /**
     * 出生年月
     *
     * @return
     */
    @Column(name = "birthday")
    private Date birthday;
    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;
    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;
    /**
     * 邮箱
     */
    @Column(name = "qq")
    private String qq;
    /**
     * 头像
     */
    @Column(name = "pic")
    private String pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}

