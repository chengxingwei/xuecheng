package com.xuecheng.security.service;

import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.utils.ResultUtil;

import java.util.List;

public interface UserInfoService {
    public List<UserInfo> list(UserInfoDTO userInfoDTO);

    public ResultUtil save(UserInfoDTO userInfoDTO);

    public ResultUtil delete(UserInfoDTO userInfoDTO);
}
