package com.strr.admin.controller;

import com.strr.admin.model.SysAuthority;
import com.strr.admin.model.SysAuthorityVO;
import com.strr.admin.model.SysUserDetails;
import com.strr.admin.service.SysAuthorityService;
import com.strr.admin.util.SysUtil;
import com.strr.base.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/sysAuthority")
public class CustomSysAuthorityController extends SysAuthorityController {
    @Autowired
    public CustomSysAuthorityController(SysAuthorityService sysAuthorityService) {
        super(sysAuthorityService);
    }

    /**
     * 用户菜单树
     * @return
     */
    @GetMapping("/userMenuTree")
    @ResponseBody
    public Result<List<SysAuthorityVO>> userMenuTree(@AuthenticationPrincipal SysUserDetails sysUserDetails) {
        List<SysAuthority> authorities = sysUserDetails.getAuthorityList();
        return Result.ok(SysUtil.buildMenuTree(authorities));
    }
}
