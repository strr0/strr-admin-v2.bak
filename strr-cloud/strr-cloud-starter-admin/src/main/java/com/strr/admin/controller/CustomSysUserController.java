package com.strr.admin.controller;

import com.strr.admin.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/sysUser")
public class CustomSysUserController extends SysUserController {
    public CustomSysUserController(SysUserService sysUserService) {
        super(sysUserService);
    }
}
