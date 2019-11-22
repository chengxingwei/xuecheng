package com.xuecheng.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.member.dto.RoleDTO;
import com.xuecheng.member.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MinHow
 * @since 2019-11-18
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    public List<Role> findRolesByMember(RoleDTO roleDTO);
    public List<Role> findRolesByMenu(RoleDTO roleDTO);
}
