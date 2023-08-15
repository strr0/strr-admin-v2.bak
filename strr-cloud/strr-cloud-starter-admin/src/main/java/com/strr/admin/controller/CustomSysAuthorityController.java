package com.strr.admin.controller;

import com.strr.admin.model.SysAuthority;
import com.strr.admin.model.SysAuthorityVO;
import com.strr.admin.service.SysAuthorityService;
import com.strr.admin.util.SysUtil;
import com.strr.base.model.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/sysAuthority")
public class CustomSysAuthorityController extends SysAuthorityController {
    public CustomSysAuthorityController(SysAuthorityService sysAuthorityService) {
        super(sysAuthorityService);
    }

    /**
     * 用户菜单树
     * @return
     */
    @GetMapping("/userMenuTree")
    @ResponseBody
    public Result<List<SysAuthorityVO>> userMenuTree(@AuthenticationPrincipal Jwt jwt) {
        List<SysAuthority> authorities = ((SysAuthorityService) getService()).listByUserId(((Long) jwt.getClaims().get("user_id")).intValue());
        return Result.ok(SysUtil.buildMenuTree(authorities));
    }
}
