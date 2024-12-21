package com.strr.system.model.vo;

import com.strr.system.model.SysResource;

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
