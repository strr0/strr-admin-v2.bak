package com.strr.admin.util;

import com.strr.admin.model.SysResource;
import com.strr.admin.model.SysResourceVO;
import com.strr.util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SysUtil {
    /**
     * 菜单树
     * @param resources
     * @return
     */
    public static List<SysResourceVO> buildMenuTree(List<SysResource> resources) {
        return TreeUtil.reverseBuildTree(resources, SysResource::getId, SysResource::getParentId, SysResource::getOrder,
                SysResourceVO::new, SysResourceVO::getChildren, SysResourceVO::setChildren);
    }

    /**
     * 差集
     * @param a
     * @param b
     * @return
     */
    public static List<Integer> subtraction(Integer[] a, Integer[] b) {
        List<Integer> result = new ArrayList<>();
        if (a != null) {
            result.addAll(Arrays.asList(a));
        }
        if (b != null) {
            result.removeAll(Arrays.asList(b));
        }
        return result;
    }
}
