package com.xuecheng.api;

import com.xuecheng.entities.system.Role;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleClientServiceFallbackFactory  implements FallbackFactory<RoleClientService> {
    @Override
    public RoleClientService create(Throwable throwable) {
        return new RoleClientService() {
            @Override
            public List<Role> roleList() {
                return null;
            }
        };
    }
}
