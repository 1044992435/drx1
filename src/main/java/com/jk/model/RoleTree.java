package com.jk.model;

import java.io.Serializable;

/**
 * <pre>com.jk.model
 * 类名称：RoleTree
 * 类描述：
 * 创建人：董睿晓
 * 创建时间：2018/11/2  1:55
 * 修改人：董睿晓
 * 修改时间：2018/11/2  1:55
 * 修改备注：
 * @version </pre>
 */
public class RoleTree implements Serializable {

    private static final long serialVersionUID = -2434545177441480872L;

    private Integer id;
    private Integer tid;
    private Integer rid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
