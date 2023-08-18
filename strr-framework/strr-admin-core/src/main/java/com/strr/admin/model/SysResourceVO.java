package com.strr.admin.model;

import java.util.List;

public class SysResourceVO extends SysResource {
    private String parentTitle;

    private List<SysResourceVO> children;

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public List<SysResourceVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysResourceVO> children) {
        this.children = children;
    }
}
