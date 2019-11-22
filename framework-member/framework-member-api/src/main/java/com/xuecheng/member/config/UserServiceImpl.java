package com.xuecheng.member.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuecheng.member.dto.RoleDTO;
import com.xuecheng.member.entity.Company;
import com.xuecheng.member.entity.Member;
import com.xuecheng.member.entity.MemberCompany;
import com.xuecheng.member.entity.Role;
import com.xuecheng.member.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MemberRoleMapper memberRoleMapper;

    @Autowired
    private MemberCompanyMapper memberCompanyMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private CompanyMapper companyMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Member member = memberMapper.selectOne(new QueryWrapper<Member>().eq("UserName",s));
        if(member == null)
        {
            throw new UsernameNotFoundException("没有该用户");
        }
        //查到User后将其封装为UserDetails的实现类的实例供程序调用
        //用该User和它对应的Role实体们构造UserDetails的实现类
        List<MemberCompany> memberCompanies = memberCompanyMapper.selectList(new QueryWrapper<MemberCompany>().eq("MemberId",member.getId()));
        List<Company> companies = new ArrayList<Company>();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setMemberId(member.getId());
        if (memberCompanies != null && memberCompanies.size() > 0){
            for (MemberCompany memberCompany : memberCompanies) {
                Company company = companyMapper.selectById(memberCompany.getCompany());
                if (company != null){
                    companies.add(company);
                }
            }
        }
        List<Role> roles = roleMapper.findRolesByMember(roleDTO);
        return new UserDetailsImpl(member,new ArrayList<Role>(roles),companies);
    }
}
