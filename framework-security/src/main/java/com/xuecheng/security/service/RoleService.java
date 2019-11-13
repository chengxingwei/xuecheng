package com.xuecheng.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.entities.system.Role;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.security.dto.RoleDTO;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.utils.Result;

public interface RoleService extends IService<Role> {
    /**
     * 获取角色列表
     * @param roleDTO
     * @return
     */
    public Result list(RoleDTO roleDTO);

    /**
     * 保存角色
     * @param roleDTO
     * @return
     */
    public Result save(RoleDTO roleDTO);

    /**
     * 删除角色
     * @param roleDTO
     * @return
     */
    public Result delete(RoleDTO roleDTO);

    /**
     * 通过用户获取角色
     * @param roleDTO
     * @return
     */
    public Result listByUser(RoleDTO roleDTO);

    /**
     * 修改角色菜单权限
     * @param roleDTO
     * @return
     */
    public Result updateRole(RoleDTO roleDTO);
}
