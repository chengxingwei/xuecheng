package com.xuecheng.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.entities.system.Menu;
import com.xuecheng.entities.system.Role;
import com.xuecheng.security.dto.MenuDTO;
import com.xuecheng.security.dto.RoleDTO;
import com.xuecheng.utils.Result;

public interface MenuService  extends IService<Menu> {
    /**
     * 获取菜单列表
     * @param menuDTO
     * @return
     */
    public Result list(MenuDTO menuDTO);

    /**
     * 保存菜单
     * @param menuDTO
     * @return
     */
    public Result save(MenuDTO menuDTO);

    /**
     * 删除菜单
     * @param menuDTO
     * @return
     */
    public Result delete(MenuDTO menuDTO);
}
