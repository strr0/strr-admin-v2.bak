package com.strr.system.controller;

import com.strr.base.controller.SCrudController;
import com.strr.base.model.Result;
import com.strr.system.model.SysResource;
import com.strr.system.model.vo.SysResourceVO;
import com.strr.system.model.vo.SysRouteVO;
import com.strr.system.service.ISysResourceService;
import com.strr.system.util.MenuUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/sysResource")
public class SysResourceController extends SCrudController<SysResource, Integer> {
    private final ISysResourceService sysResourceService;

    public SysResourceController(ISysResourceService sysResourceService) {
        this.sysResourceService = sysResourceService;
    }

    @Override
    protected ISysResourceService getService() {
        return sysResourceService;
    }

    /**
     * 菜单树
     */
    @GetMapping("/menuTree")
    public Result<List<SysResourceVO>> menuTree(SysResource param) {
        List<SysResource> sysResourceList = sysResourceService.listByParam(param);
        return Result.ok(MenuUtil.buildMenuTree(sysResourceList));
    }

    /**
     * 删除权限
     */
    @DeleteMapping("/removeInfo")
    public Result<Void> removeInfo(Integer id) {
        sysResourceService.removeInfo(id);
        return Result.ok();
    }

    /**
     * 用户菜单树
     * @return
     */
    @GetMapping("/getUserRoutes")
    public Result<?> getUserRoutes(@AuthenticationPrincipal Jwt jwt) {
        List<SysResource> resources = getService().listByUserId(((Long) jwt.getClaims().get("userId")).intValue());
        List<SysRouteVO> routes = MenuUtil.buildRouteTree(resources);
        String home = MenuUtil.getFirstRoute(routes);
        return Result.ok(new HashMap<String, Object>(){{
            put("routes", routes);
            put("home", home);
        }});
    }
}
