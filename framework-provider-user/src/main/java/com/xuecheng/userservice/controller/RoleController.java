package com.xuecheng.userservice.controller;

import com.xuecheng.entities.system.Role;
import com.xuecheng.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/system/rolelist")
    public List<Role> roleList()
    {
        return  roleRepository.findAll();
    }

    @RequestMapping("/system/rolelist1")
    public String asadfs()
    {
        return  "123123";
    }
}
