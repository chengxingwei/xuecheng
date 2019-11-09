package com.xuecheng.security.service.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.enums.ResultTypeEnum;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.security.mapper.UserInfoMapper;
import com.xuecheng.security.repository.UserInfoRepository;
import com.xuecheng.security.service.UserInfoService;
import com.xuecheng.utils.Pagination;
import com.xuecheng.utils.Result;
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
    public Result list(UserInfoDTO userInfoDTO) {
        Result resultUtil = new Result();
        resultUtil.setCode(0);
        if (Objects.equals(0,userInfoDTO.getPageIndex())){//不分页
            resultUtil.setData(userInfoMapper.list(userInfoDTO));
        }else{//分页
            Page page = PageHelper.startPage(userInfoDTO.getPageIndex(),userInfoDTO.getPageSize());
            List<UserInfo> userInfos = userInfoMapper.list(userInfoDTO);
            resultUtil.setData(userInfos);
            Pagination pagination = new Pagination();
            pagination.setTotal(page.getTotal()).setCurrent(userInfoDTO.getPageIndex()).setPageSize(userInfoDTO.getPageSize());
            resultUtil.setPagination(pagination);
        }
        return resultUtil;
    }

    @Override
    public Result save(UserInfoDTO userInfoDTO) {
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
                return new Result(ResultTypeEnum.SUCCESS.toValue(),"用户不存在",null);
            }
        }
        return new Result(null);
    }

    @Override
    public Result delete(UserInfoDTO userInfoDTO) {
        if (userInfoDTO.getIds() != null && userInfoDTO.getIds().length > 0){
            for (Long id : userInfoDTO.getIds()) {
                userInfoRepository.deleteById(id);
            }
        }else{
            return new Result().setCode(ResultTypeEnum.FAIL.toValue()).setMsg("参数错误");
        }
        return new Result().setMsg("操作成功");
    }
}
