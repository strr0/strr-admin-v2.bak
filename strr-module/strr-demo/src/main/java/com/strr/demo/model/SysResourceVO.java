package com.strr.demo.model;

import java.util.List;

public class SysResourceVO extends SysResource {
    private List<SysResourceVO> children;

    public List<SysResourceVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysResourceVO> children) {
        this.children = children;
    }
}
