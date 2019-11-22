package com.xuecheng.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.member.entity.Member;
import com.xuecheng.member.mapper.MemberMapper;
import com.xuecheng.member.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MinHow
 * @since 2019-11-18
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
