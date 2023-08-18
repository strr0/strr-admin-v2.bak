package com.strr.admin.controller;

import com.strr.admin.model.SysResource;
import com.strr.admin.model.SysRouteVO;
import com.strr.admin.service.SysResourceService;
import com.strr.admin.util.SysUtil;
import com.strr.base.model.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/routeTree")
    public Result<List<SysRouteVO>> routeTree(@AuthenticationPrincipal Jwt jwt) {
        List<SysResource> resources = ((SysResourceService) getService()).listByUserId(((Long) jwt.getClaims().get("user_id")).intValue());
        return Result.ok(SysUtil.buildRouteTree(resources));
    }
}
