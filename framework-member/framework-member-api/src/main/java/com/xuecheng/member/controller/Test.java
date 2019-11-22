package com.xuecheng.member.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Test", description = "测试接口")
@RestController
public class Test {

    @GetMapping("/testt12")
    @ApiOperation(value = "test1", notes = "测试111")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dto",value = "test",required = true,dataType = "HttpServletResponse"),
    })
    public String test1(){
        return "123";
    }
}
