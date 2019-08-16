package com.xuecheng.api;

import com.xuecheng.entities.system.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;


@FeignClient(value = "framework-user",fallbackFactory=RoleClientServiceFallbackFactory.class)
public interface RoleClientService {

    @RequestMapping(value = "/system/rolelist",method = RequestMethod.GET)
    public List<Role> roleList();

}
