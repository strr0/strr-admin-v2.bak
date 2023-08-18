package com.strr.admin.model;

import java.util.List;

public class SysRouteVO {
    /**
     * 路由名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 路由组件
     */
    private String component;

    /**
     * 子路由
     */
    private List<SysRouteVO> children;

    /**
     * 路由描述
     */
    private SysRouteMetaVO meta;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public List<SysRouteVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysRouteVO> children) {
        this.children = children;
    }

    public SysRouteMetaVO getMeta() {
        return meta;
    }

    public void setMeta(SysRouteMetaVO meta) {
        this.meta = meta;
    }
}
