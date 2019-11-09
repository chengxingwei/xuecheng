package com.xuecheng.security.controller;

import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.security.service.UserInfoService;
import com.xuecheng.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    public Result delete(@RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.delete(userInfoDTO);
    }
}
