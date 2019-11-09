package com.xuecheng.security.controller;

import com.xuecheng.api.RoleClientService;
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

}
