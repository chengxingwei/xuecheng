package com.xuecheng.security.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.enums.ResultTypeEnum;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.security.mapper.UserInfoMapper;
import com.xuecheng.security.repository.UserInfoRepository;
import com.xuecheng.security.service.UserInfoService;
import com.xuecheng.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> list(UserInfoDTO userInfoDTO) {
        if (Objects.equals(0,userInfoDTO.getPageIndex())){//不分页
            return userInfoMapper.list(userInfoDTO);
        }else{//分页
            PageHelper.startPage(2,3);
            return userInfoMapper.list(userInfoDTO);
        }
    }

    @Override
    public ResultUtil save(UserInfoDTO userInfoDTO) {
        if (userInfoDTO.getId() == null){//添加
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(userInfoDTO.getUserName())
                    .setMobile(userInfoDTO.getMobile());
            userInfoRepository.save(userInfo);
        }else{//修改
            Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(userInfoDTO.getId());
            if (optionalUserInfo.isPresent()){
                UserInfo userInfo = optionalUserInfo.get();
                userInfo.setMobile(userInfoDTO.getMobile());
                userInfoRepository.save(userInfo);
            }else{
                return new ResultUtil(ResultTypeEnum.SUCCESS.toValue(),"用户不存在",null);
            }
        }
        return new ResultUtil(null);
    }

    @Override
    public ResultUtil delete(UserInfoDTO userInfoDTO) {
        if (userInfoDTO.getIds() != null && userInfoDTO.getIds().length > 0){
            for (Long id : userInfoDTO.getIds()) {
                userInfoRepository.deleteById(id);
            }
        }else{
            return new ResultUtil().setCode(ResultTypeEnum.FAIL.toValue()).setMsg("参数错误");
        }
        return new ResultUtil().setMsg("操作成功");
    }
}
