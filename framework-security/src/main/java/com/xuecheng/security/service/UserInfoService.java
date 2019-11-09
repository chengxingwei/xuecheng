package com.xuecheng.security.service;

import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.utils.Result;

public interface UserInfoService {
    public Result list(UserInfoDTO userInfoDTO);

    public Result save(UserInfoDTO userInfoDTO);

    public Result delete(UserInfoDTO userInfoDTO);
}
