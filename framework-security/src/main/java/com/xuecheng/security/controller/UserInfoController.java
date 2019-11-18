package com.xuecheng.security.controller;

import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.security.service.UserInfoService;
import com.xuecheng.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {



    @Autowired
    private UserInfoService userInfoService;

    /**
     *
     * @return
     */
    @GetMapping("/list")
    public Result list(UserInfoDTO userInfoDTO){
        return userInfoService.list(userInfoDTO);
    }

    @PostMapping("/save")
    public Result save(@RequestBody UserInfoDTO userInfoDTO ){
        return userInfoService.save(userInfoDTO);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.delete(userInfoDTO);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public Result getUser(Principal principal) {
        Result result = new Result();
        result.setCode(0).setData(principal);
        return result;
    }

    @PostMapping("/role")
    public Result updateRoles(@RequestBody UserInfoDTO userInfoDTO ){
        return userInfoService.updateRoles(userInfoDTO);
    }

    @GetMapping("/{id}")
    public Result list(@PathVariable Long id){
        Result result = new Result();
        result.setCode(0);
        if (id == null){
            result.setCode(1).setMsg("参数错误");
        }else{
            UserInfo userInfo = userInfoService.getById(id);
            if (userInfo != null){
                userInfo.setPassword("");
                result.setData(userInfo);
            }else{
                result.setCode(1).setMsg("数据不存在");
            }
        }
        return result;
    }



}
