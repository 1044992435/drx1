package com.jk.model;

import java.io.Serializable;

/**
 * <pre>com.jk.model
 * 类名称：Role
 * 类描述：
 * 创建人：董睿晓
 * 创建时间：2018/11/1  15:47
 * 修改人：董睿晓
 * 修改时间：2018/11/1  15:47
 * 修改备注：
 * @version </pre>
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 7484661769934319916L;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    private String rname;



}
