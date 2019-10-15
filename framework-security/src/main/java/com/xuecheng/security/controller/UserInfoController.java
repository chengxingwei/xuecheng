package com.xuecheng.security.controller;

import com.github.pagehelper.PageHelper;
import com.xuecheng.enums.ResultTypeEnum;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.security.service.UserInfoService;
import com.xuecheng.utils.ResultUtil;
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
    public ResultUtil list(UserInfoDTO userInfoDTO){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(ResultTypeEnum.SUCCESS.toValue())
                .setData(userInfoService.list(userInfoDTO));
        return resultUtil;
    }

    @PostMapping("/save")
    public ResultUtil save(@RequestBody UserInfoDTO userInfoDTO ){
        return userInfoService.save(userInfoDTO);
    }

    @DeleteMapping
    public ResultUtil delete(@RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.delete(userInfoDTO);
    }
}
