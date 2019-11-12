package com.xuecheng.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.security.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    public List<UserInfoVO> list(UserInfoDTO dto);
}
