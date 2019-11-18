package com.xuecheng.security.controller;

import com.xuecheng.entities.system.Menu;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.security.dto.MenuDTO;
import com.xuecheng.security.dto.RoleDTO;
import com.xuecheng.security.service.MenuService;
import com.xuecheng.security.service.RoleService;
import com.xuecheng.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result roleList(MenuDTO menuDTO){
        return menuService.list(menuDTO);
    }



    @PostMapping("/save")
    public Result save(@RequestBody MenuDTO menuDTO ){
        return menuService.save(menuDTO);
    }



    @PostMapping("/delete")
    public Result delete(@RequestBody MenuDTO menuDTO){
        return menuService.delete(menuDTO);
    }
    @GetMapping("/{id}")
    public Result list(@PathVariable Long id){
        Result result = new Result();
        result.setCode(0);
        if (id == null){
            result.setCode(1).setMsg("参数错误");
        }else{
            Menu menu = menuService.getById(id);
            if (menu != null){
                result.setData(menu);
            }else{
                result.setCode(1).setMsg("数据不存在");
            }
        }
        return result;
    }



}
