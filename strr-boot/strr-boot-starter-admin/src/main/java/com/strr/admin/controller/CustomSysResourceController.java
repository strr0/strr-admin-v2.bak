package com.strr.admin.controller;

import com.strr.admin.model.SysResource;
import com.strr.admin.model.SysRouteVO;
import com.strr.admin.model.SysUserDetails;
import com.strr.admin.service.SysResourceService;
import com.strr.admin.util.SysUtil;
import com.strr.base.model.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/sysResource")
public class CustomSysResourceController extends SysResourceController {
    public CustomSysResourceController(SysResourceService sysResourceService) {
        super(sysResourceService);
    }

    /**
     * 用户菜单树
     * @return
     */
    @GetMapping("/getUserRoutes")
    public Result<?> getUserRoutes(@AuthenticationPrincipal SysUserDetails sysUserDetails) {
        List<SysResource> resources = sysUserDetails.getResourceList();
        List<SysRouteVO> routes = SysUtil.buildRouteTree(resources);
        String home = SysUtil.getFirstRoute(routes);
        return Result.ok(new HashMap<String, Object>(){{
            put("routes", routes);
            put("home", home);
        }});
    }
}
