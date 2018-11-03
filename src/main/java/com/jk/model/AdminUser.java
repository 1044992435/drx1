package com.jk.model;


import java.io.Serializable;

/**
 * <pre>com.jk.model
 * 类名称：AdminUser
 * 类描述：后台登录实体类
 * 创建人：王震
 * 创建时间：2018/10/30  17:27
 * 修改人：王震
 * 修改时间：2018/10/30  17:27
 * 修改备注：
 * @version </pre>
 */
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 7484661769934319916L;
    private Integer uid;
    private String username;
    private String password;
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
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
}
