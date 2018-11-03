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
    //业务字段
    private String loginNumber;
    private String accountSid;
    private String smsContent;
    private String to;
    private String timestamp;
    private String sig;
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

    public String getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(String loginNumber) {
        this.loginNumber = loginNumber;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }


}
