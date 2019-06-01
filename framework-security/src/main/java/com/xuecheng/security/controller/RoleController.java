package com.xuecheng.security.controller;

import com.xuecheng.api.RoleClientService;
import com.xuecheng.entities.system.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class RoleController {

    @Autowired
    RoleClientService roleClientService;

    @RequestMapping(value = "/security/rolelist",method = RequestMethod.GET)
    public List<Role> roleList(){
        return roleClientService.roleList();
    }


    @RequestMapping(value = "/security/getRole",method = RequestMethod.GET)
    public Role getRole(){
        return new Role();
    }


    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public void login(@Param("username") String username,@Param("password") String password){
        System.out.println("username:"+username+";password:"+password);
    }

    @RequestMapping(value = "/users/current", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public Principal getUser(Principal principal) {
        System.out.println("sdfsdfsdf");
        return principal;
    }
}
