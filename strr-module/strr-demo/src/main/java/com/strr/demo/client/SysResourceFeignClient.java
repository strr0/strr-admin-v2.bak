package com.strr.demo.client;

import com.strr.base.model.Result;
import com.strr.demo.model.SysResource;
import com.strr.demo.model.SysResourceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("strr-system")
public interface SysResourceFeignClient {
    @GetMapping("/admin/sysResource/menuTree")
    Result<List<SysResourceVO>> menuTree(@SpringQueryMap SysResource param);
}
