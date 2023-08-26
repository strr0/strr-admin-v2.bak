package com.strr.admin.controller;

import com.strr.admin.service.SysRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/sysRole")
public class SysRoleController extends DefaultSysRoleController {
    public SysRoleController(SysRoleService sysRoleService) {
        super(sysRoleService);
    }
}
