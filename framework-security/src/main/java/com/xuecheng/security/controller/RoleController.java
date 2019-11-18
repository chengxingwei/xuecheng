package com.xuecheng.security.controller;

import com.xuecheng.api.RoleClientService;
import com.xuecheng.entities.system.Menu;
import com.xuecheng.entities.system.Role;
import com.xuecheng.security.dto.RoleDTO;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.security.service.RoleService;
import com.xuecheng.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result roleList(RoleDTO roleDTO){
        return roleService.list(roleDTO);
    }


    @PostMapping("/save")
    public Result save(@RequestBody RoleDTO roleDTO ){
        return roleService.save(roleDTO);
    }



    @PostMapping("/delete")
    public Result delete(@RequestBody RoleDTO roleDTO){
        return roleService.delete(roleDTO);
    }

    @PostMapping("/menu")
    public Result updateMenu(@RequestBody RoleDTO roleDTO ){
        return roleService.updateRole(roleDTO);
    }


    @GetMapping("/{id}")
    public Result list(@PathVariable Long id){
        Result result = new Result();
        result.setCode(0);
        if (id == null){
            result.setCode(1).setMsg("参数错误");
        }else{
            Role role = roleService.getById(id);
            if (role != null){
                result.setData(role);
            }else{
                result.setCode(1).setMsg("数据不存在");
            }
        }
        return result;
    }
}
