package com.xuecheng.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.utils.Result;

public interface UserInfoService extends IService<UserInfo> {

    public Result list(UserInfoDTO userInfoDTO);

    public Result save(UserInfoDTO userInfoDTO);

    public Result delete(UserInfoDTO userInfoDTO);

    Result updateRoles(UserInfoDTO userInfoDTO);
}
