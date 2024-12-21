package com.strr.system.controller;

import com.strr.base.controller.SCrudController;
import com.strr.base.model.Result;
import com.strr.system.model.SysProperties;
import com.strr.system.service.ISysPropertiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sysProperties")
public class SysPropertiesController extends SCrudController<SysProperties, Integer> {
    private final ISysPropertiesService sysPropertiesService;

    public SysPropertiesController(ISysPropertiesService sysPropertiesService) {
        this.sysPropertiesService = sysPropertiesService;
    }

    @Override
    protected ISysPropertiesService getService() {
        return sysPropertiesService;
    }

    /**
     * 批量新增
     */
    @PostMapping("/batchSave")
    public Result<Integer> batchSave(@RequestBody List<SysProperties> list) {
        int r = sysPropertiesService.batchSave(list);
        if (r > 0) {
            return Result.ok(r);
        }
        return Result.error();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/batchRemove")
    public Result<Void> batchRemove(String application) {
        int r = sysPropertiesService.batchRemove(application);
        if (r > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 获取应用列表
     */
    @GetMapping("/listApplication")
    public Result<List<SysProperties>> listApplication(String application) {
        List<SysProperties> list = sysPropertiesService.listApplication(application);
        return Result.ok(list);
    }

    /**
     * 获取属性列表
     */
    @GetMapping("/listProperties")
    public Result<List<SysProperties>> listProperties(String application) {
        List<SysProperties> list = sysPropertiesService.listProperties(application);
        return Result.ok(list);
    }
}
