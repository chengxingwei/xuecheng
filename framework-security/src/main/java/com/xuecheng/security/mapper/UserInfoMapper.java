package com.xuecheng.security.mapper;

import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.security.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    public List<UserInfo> list(UserInfoDTO dto);
}
