package com.strr.admin.model;

import com.strr.base.annotation.SColumn;
import com.strr.base.annotation.SId;
import com.strr.base.annotation.STable;

import java.util.Date;

@STable("sys_role")
public class SysRole {
    /**
     * 主键
     */
    @SId
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 标题
     */
    @SColumn(fuzzy = true)
    private String title;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 创建时间
     */
    @SColumn("create_time")
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updator;

    /**
     * 更新时间
     */
    @SColumn("update_time")
    private Date updateTime;

    /**
     * 状态
     */
    private String status;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
