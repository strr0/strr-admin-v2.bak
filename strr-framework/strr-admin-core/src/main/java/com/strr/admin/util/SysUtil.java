package com.strr.admin.util;

import com.strr.admin.model.SysResource;
import com.strr.admin.model.SysResourceVO;
import com.strr.admin.model.SysRouteMetaVO;
import com.strr.admin.model.SysRouteVO;
import com.strr.util.TreeUtil;
import org.springframework.beans.BeanUtils;

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
        return new TreeUtil<SysResource, SysResourceVO, Integer>(resources)
                .withGetId(SysResource::getId)
                .withGetPid(SysResource::getParentId)
                .withGetOrder(SysResource::getOrder)
                .withGetChildren(SysResourceVO::getChildren)
                .withSetChildren(SysResourceVO::setChildren)
                .withTransform(resource -> {
                    SysResourceVO vo = new SysResourceVO();
                    BeanUtils.copyProperties(resource, vo);
                    return vo;
                }).build();
    }

    /**
     * 路由树
     * @param resources
     * @return
     */
    public static List<SysRouteVO> buildRouteTree(List<SysResource> resources) {
        return new TreeUtil<SysResource, SysRouteVO, Integer>(resources)
                .withGetId(SysResource::getId)
                .withGetPid(SysResource::getParentId)
                .withGetOrder(item -> item.getMeta().getOrder())
                .withGetChildren(SysRouteVO::getChildren)
                .withSetChildren(SysRouteVO::setChildren)
                .withTransform(resource -> {
                    SysRouteVO route = new SysRouteVO();
                    BeanUtils.copyProperties(resource, route);
                    SysRouteMetaVO meta = new SysRouteMetaVO();
                    BeanUtils.copyProperties(resource, meta);
                    route.setMeta(meta);
                    return route;
                }).build();
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
