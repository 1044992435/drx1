package com.jk.model;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>com.jk.model
 * 类名称：Tree
 * 类描述：
 * 创建人：董睿晓
 * 创建时间：2018/11/1  20:41
 * 修改人：董睿晓
 * 修改时间：2018/11/1  20:41
 * 修改备注：
 * @version </pre>
 */
public class Tree  implements Serializable {
    private static final long serialVersionUID = 4944958333340466647L;
    private String  id;
    private String text;
    private String url;
    private String pid;
    private List<Tree> nodes;
    private List<Tree> children;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public List<Tree> getNodes() {
        return nodes;
    }
    public void setNodes(List<Tree> nodes) {
        this.nodes = nodes;
    }
    public List<Tree> getChildren() {
        return children;
    }
    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}
