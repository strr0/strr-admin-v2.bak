package com.strr.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.strr.base.annotation.Column;
import com.strr.base.annotation.Id;
import com.strr.base.annotation.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Table("sys_resource")
public class SysResource {
    /**
     * 编号
     */
    @Id
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 路由组件
     */
    private String component;

    /**
     * 标题
     */
    @Column(fuzzy = true)
    private String title;

    /**
     * 类型(0.目录 1.菜单 2.按钮)
     */
    private String type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 隐藏
     */
    private Boolean hide;

    /**
     * 父菜单
     */
    @Column("parent_id")
    private Integer parentId;

    /**
     * 排序
     */
    @Column("`order`")
    private Integer order;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 创建时间
     */
    @Column("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updator;

    /**
     * 更新时间
     */
    @Column("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
