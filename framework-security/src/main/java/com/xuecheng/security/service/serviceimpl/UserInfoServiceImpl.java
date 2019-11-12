package com.xuecheng.security.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuecheng.entities.system.UserInfo;
import com.xuecheng.entities.system.UserRole;
import com.xuecheng.enums.ResultTypeEnum;
import com.xuecheng.security.dto.UserInfoDTO;
import com.xuecheng.security.mapper.UserInfoMapper;
import com.xuecheng.security.mapper.UserRoleMapper;
import com.xuecheng.security.service.UserInfoService;
import com.xuecheng.security.vo.UserInfoVO;
import com.xuecheng.utils.Pagination;
import com.xuecheng.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper,UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Result list(UserInfoDTO userInfoDTO) {
        Result resultUtil = new Result();
        resultUtil.setCode(0);
        if (Objects.equals(0,userInfoDTO.getPageIndex())){//不分页
            resultUtil.setData(userInfoMapper.list(userInfoDTO));
        }else{//分页
            Page page = PageHelper.startPage(userInfoDTO.getPageIndex(),userInfoDTO.getPageSize());
            List<UserInfoVO> userInfos = userInfoMapper.list(userInfoDTO);
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
                    .setMobile(userInfoDTO.getMobile())
                    .setNickName(userInfoDTO.getNickName())
                    .setRealName(userInfoDTO.getRealName())
                    .setStatus(userInfoDTO.getStatus());
            userInfo.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
            userInfoMapper.insert(userInfo);
        }else{//修改
            UserInfo userInfo = userInfoMapper.selectById(userInfoDTO.getId());
            if (userInfo != null){
                userInfo.setUserName(userInfoDTO.getUserName())
                        .setMobile(userInfoDTO.getMobile())
                        .setNickName(userInfoDTO.getNickName())
                        .setRealName(userInfoDTO.getRealName())
                        .setStatus(userInfoDTO.getStatus());
                if (!Objects.equals(userInfoDTO.getPassword(),"******")){
                    userInfo.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
                }
                userInfoMapper.update(userInfo,new QueryWrapper<UserInfo>().eq("id",userInfoDTO.getId()));
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
                userInfoMapper.deleteById(id);
            }
        }else{
            return new Result().setCode(ResultTypeEnum.FAIL.toValue()).setMsg("参数错误");
        }
        return new Result().setMsg("操作成功");
    }

    @Override
    public Result updateRoles(UserInfoDTO userInfoDTO) {
        UserInfo userInfo =  userInfoMapper.selectById(userInfoDTO.getId());
        if (userInfo == null){
            return new Result().setCode(ResultTypeEnum.FAIL.toValue()).setMsg("用户不存在");
        }else{
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq("userinfoId",userInfo.getId()));
            if (userInfoDTO.getRoleIds() != null){
                for (Long roleId : userInfoDTO.getRoleIds()) {
                    UserRole userRole = new UserRole();
                    userRole.setUserInfoID(userInfo.getId());
                    userRole.setRoleID(roleId);
                    userRoleMapper.insert(userRole);
                }
            }
        }
        return new Result().setMsg("操作成功");
    }
}
