package com.jk.model;

import java.io.Serializable;

/**
 * <pre>com.jk.model
 * 类名称：AdminRole
 * 类描述：
 * 创建人：董睿晓
 * 创建时间：2018/11/1  16:10
 * 修改人：董睿晓
 * 修改时间：2018/11/1  16:10
 * 修改备注：
 * @version </pre>
 */
public class AdminRole implements Serializable {
    private static final long serialVersionUID = -2434545177441480872L;

    private Integer id;
    private Integer uid;
    private Integer rid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
