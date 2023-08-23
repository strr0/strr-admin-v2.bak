package com.strr.admin.util;

import com.strr.admin.model.SysResource;
import com.strr.admin.model.vo.SysResourceVO;
import com.strr.admin.model.vo.SysRouteMetaVO;
import com.strr.admin.model.vo.SysRouteVO;
import com.strr.util.TreeUtil;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SysUtil {
    private static final String DIR_TYPE = "0";  // 目录
    private static final String MENU_TYPE = "1";  // 菜单
    private static final String BTN_TYPE = "2";  // 按钮

    private static final String BASIC_LAYOUT = "basic";  // 基础布局
    private static final String BLANK_LAYOUT = "blank";  // 空白布局

    /**
     * 菜单树
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
                })
                // 添加按钮
                .withAddButton((resource, route) -> {
                    if (!BTN_TYPE.equals(resource.getType())) {
                        return false;
                    }
                    List<String> buttons = route.getMeta().getButtons();
                    if (buttons == null) {
                        buttons = new ArrayList<>();
                        route.getMeta().setButtons(buttons);
                    }
                    buttons.add(resource.getName());
                    return true;
                })
                // 添加布局
                .withAddLayout((resource, route) -> {
                    if (MENU_TYPE.equals(resource.getType())) {
                        route.getMeta().setSingleLayout(BASIC_LAYOUT);
                    }
                })
                .build();
    }

    /**
     * 获取第一个路由名称
     */
    public static String getFirstRoute(List<SysRouteVO> routes) {
        if (routes == null || routes.isEmpty()) {
            return null;
        }
        SysRouteVO route = routes.get(0);
        if (route.getChildren() == null || route.getChildren().isEmpty()) {
            return route.getName();
        }
        return getFirstRoute(route.getChildren());
    }
}
