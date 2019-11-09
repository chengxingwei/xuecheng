package com.xuecheng.security.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuecheng.entities.system.Role;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.enums.ResultTypeEnum;
import com.xuecheng.security.dto.RoleDTO;
import com.xuecheng.security.mapper.RoleMapper;
import com.xuecheng.security.mapper.UserInfoMapper;
import com.xuecheng.security.service.RoleService;
import com.xuecheng.security.service.UserInfoService;
import com.xuecheng.utils.Pagination;
import com.xuecheng.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Result list(RoleDTO roleDTO) {
        Result result = new Result();
        result.setCode(0);
        if (Objects.equals(roleDTO.getPageIndex(),0)){//不分页
            List<Role> roles = roleMapper.list(roleDTO);
            result.setData(roles);
        }else{//分页
            Page page = PageHelper.startPage(roleDTO.getPageIndex(),roleDTO.getPageSize());
            List<Role> roles = roleMapper.list(roleDTO);
            result.setData(roles);
            Pagination pagination = new Pagination();
            pagination.setTotal(page.getTotal()).setCurrent(roleDTO.getPageIndex()).setPageSize(roleDTO.getPageSize());
            result.setPagination(pagination);
        }
        return result;
    }

    @Override
    public Result save(RoleDTO roleDTO) {
        if (roleDTO.getId() == null){//添加
            Role role = new Role();
            role.setRoleName(roleDTO.getRoleName());
            role.setStatus(1);
            roleMapper.insert(role);
        }else{//修改
            Role role = roleMapper.selectById(roleDTO.getId());
            if (role != null){
                role.setRoleName(roleDTO.getRoleName());
                roleMapper.update(role,new QueryWrapper<Role>().eq("id",roleDTO.getId()));
            }else{
                return new Result(ResultTypeEnum.SUCCESS.toValue(),"角色不存在",null);
            }
        }
        return new Result(null);
    }

    @Override
    public Result delete(RoleDTO roleDTO) {
        if (roleDTO.getIds() != null && roleDTO.getIds().length > 0){
            for (Long id : roleDTO.getIds()) {
                roleMapper.deleteById(id);
            }
        }else{
            return new Result().setCode(ResultTypeEnum.FAIL.toValue()).setMsg("参数错误");
        }
        return new Result().setMsg("操作成功");
    }

    @Override
    public Result listByUser(RoleDTO roleDTO) {
        List<Role> roles = roleMapper.findRolesByUser(roleDTO);
        Result result = new Result();
        result.setCode(0).setData(result);
        return result;
    }
}
