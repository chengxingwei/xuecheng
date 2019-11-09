package com.xuecheng.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.entities.system.Role;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.security.dto.RoleDTO;
import com.xuecheng.security.dto.RoleMenuDTO;
import com.xuecheng.security.dto.UserInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {
    public List<Role> findRolesByUser(RoleDTO roleDTO);
    public List<Role> findRolesByMenu(RoleDTO roleDTO);
    public List<Role> list(RoleDTO roleDTO);
}
