package com.xuecheng.security.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuecheng.entities.system.Role;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.entities.system.UserRole;
import com.xuecheng.security.dto.RoleDTO;
import com.xuecheng.security.mapper.RoleMapper;
import com.xuecheng.security.mapper.UserInfoMapper;
import com.xuecheng.security.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("UserName",s));
        if(userInfo == null)
        {
            throw new UsernameNotFoundException("没有该用户");
        }
        //查到User后将其封装为UserDetails的实现类的实例供程序调用
        //用该User和它对应的Role实体们构造UserDetails的实现类
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setUserId(userInfo.getId());
        List<Role> roles = roleMapper.findRolesByUser(roleDTO);
        return new UserDetailsImpl(userInfo,new ArrayList<>(roles));
    }
}
