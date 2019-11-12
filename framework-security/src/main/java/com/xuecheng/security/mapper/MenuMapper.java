package com.xuecheng.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.entities.system.Menu;
import com.xuecheng.entities.system.Role;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.security.dto.MenuDTO;
import com.xuecheng.security.dto.UserInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    public List<Menu> list(MenuDTO dto);
}
