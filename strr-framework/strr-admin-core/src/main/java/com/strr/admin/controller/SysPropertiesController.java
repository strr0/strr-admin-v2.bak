package com.strr.admin.controller;

import com.strr.admin.model.SysProperties;
import com.strr.admin.service.SysPropertiesService;
import com.strr.base.model.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/sysProperties")
public class SysPropertiesController {
    private final SysPropertiesService sysPropertiesService;

    public SysPropertiesController(SysPropertiesService sysPropertiesService) {
        this.sysPropertiesService = sysPropertiesService;
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    @ResponseBody
    public Result<SysProperties> save(SysProperties entity) {
        int r = sysPropertiesService.save(entity);
        if (r > 0) {
            return Result.ok(entity);
        }
        return Result.error();
    }

    /**
     * 批量新增
     */
    @PostMapping("/batchSave")
    @ResponseBody
    public Result<Integer> batchSave(@RequestBody List<SysProperties> list) {
        int r = sysPropertiesService.batchSave(list);
        if (r > 0) {
            return Result.ok(r);
        }
        return Result.error();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ResponseBody
    public Result<SysProperties> update(SysProperties entity) {
        int r = sysPropertiesService.update(entity);
        if (r > 0) {
            return Result.ok(entity);
        }
        return Result.error();
    }

    /**
     * 删除
     */
    @DeleteMapping("/remove")
    @ResponseBody
    public Result<Void> remove(Integer id) {
        int r = sysPropertiesService.remove(id);
        if (r > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/batchRemove")
    @ResponseBody
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
    @ResponseBody
    public Result<List<SysProperties>> listApplication(String application) {
        List<SysProperties> list = sysPropertiesService.listApplication(application);
        return Result.ok(list);
    }

    /**
     * 获取属性列表
     */
    @GetMapping("/listProperties")
    @ResponseBody
    public Result<List<SysProperties>> listProperties(String application) {
        List<SysProperties> list = sysPropertiesService.listProperties(application);
        return Result.ok(list);
    }
}
