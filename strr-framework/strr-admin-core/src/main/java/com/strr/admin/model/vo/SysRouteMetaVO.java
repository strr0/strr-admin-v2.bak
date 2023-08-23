package com.strr.admin.model.vo;

import java.util.List;

public class SysRouteMetaVO {
    /**
     * 布局
     */
    private String singleLayout;

    /**
     * 路由标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 是否需要授权
     */
    private Boolean requiresAuth = true;

    /**
     * 按钮
     */
    private List<String> buttons;

    public String getSingleLayout() {
        return singleLayout;
    }

    public void setSingleLayout(String singleLayout) {
        this.singleLayout = singleLayout;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getRequiresAuth() {
        return requiresAuth;
    }

    public void setRequiresAuth(Boolean requiresAuth) {
        this.requiresAuth = requiresAuth;
    }

    public List<String> getButtons() {
        return buttons;
    }

    public void setButtons(List<String> buttons) {
        this.buttons = buttons;
    }
}
