package com.xuecheng.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.entities.system.Role;
import com.xuecheng.entities.system.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {
}
