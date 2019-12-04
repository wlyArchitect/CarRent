package com.wh.sys.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 左边导航树:工具类  (树节点)
 *
 *
 *  @JsonProperty: 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称
 *
 * @author 万浩
 * @data 2019/11/17 10:28
 * @description
 */
public class TreeNodeUtils {
    private Integer id;
    /**
     * 数据库中查询出的数据没有层级关系
     * 需要靠 id pid 组装层级关系,前台展示使用list数据格式(靠parentId)
     * todo 用于菜单管理的左边tree 在前台进行修改操作展示  layui+style风格
     */
    @JsonProperty("parentId")
    private Integer pid;
    private String title;
    private String icon;
    private String href;
    private Boolean spread;
    private String target;
    /**
     * 参照navs.json的格式  children放的又是一个数组,放的对象就是本身
     */
    private List<TreeNodeUtils> children = new ArrayList<>();
    /**
     * 复选树的必要属性,用于角色分配菜单
     * 选中就是"1"
     * 没选中就是"0"
     */
    private String checkArr="0";

    /**
     * 左侧导航树的构造器
     */
    public TreeNodeUtils(Integer id, Integer pid, String title, String icon, String href, Boolean spread, String target) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
        this.target = target;
    }

    /**
     * d-tree的复选树使用,供角色分配菜单
     */
    public TreeNodeUtils(Integer id, Integer pid, String title, Boolean spread, String checkArr) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
        this.checkArr = checkArr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<TreeNodeUtils> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNodeUtils> children) {
        this.children = children;
    }

    public String getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String checkArr) {
        this.checkArr = checkArr;
    }
}
