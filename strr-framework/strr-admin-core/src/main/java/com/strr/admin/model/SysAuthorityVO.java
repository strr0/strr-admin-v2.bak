package com.strr.admin.model;

import java.util.List;

public class SysAuthorityVO extends SysAuthority {
    private String parentTitle;

    private List<SysAuthorityVO> children;

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public List<SysAuthorityVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysAuthorityVO> children) {
        this.children = children;
    }
}
