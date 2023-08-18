package com.strr.admin.controller;

import com.strr.admin.service.SysPropertiesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/sysProperties")
public class CustomSysPropertiesController extends SysPropertiesController {
    public CustomSysPropertiesController(SysPropertiesService sysPropertiesService) {
        super(sysPropertiesService);
    }
}
