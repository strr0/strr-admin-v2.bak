package com.strr.admin.util;

import com.strr.admin.model.SysAuthority;
import com.strr.admin.model.SysAuthorityVO;
import com.strr.util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SysUtil {
    /**
     * 菜单树
     * @param authorities
     * @return
     */
    public static List<SysAuthorityVO> buildMenuTree(List<SysAuthority> authorities) {
        return TreeUtil.reverseBuildTree(authorities, SysAuthority::getId, SysAuthority::getParentId, SysAuthority::getSeq,
                SysAuthorityVO::new, SysAuthorityVO::getChildren, SysAuthorityVO::setChildren);
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
