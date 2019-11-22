package com.xuecheng.member.config;

import com.xuecheng.member.entity.Company;
import com.xuecheng.member.entity.Member;
import com.xuecheng.member.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {

    private String username;

    private String password;

    //包含着用户对应的所有Role，在使用时调用者给对象注入roles
    private List<Role> roles;

    private String mobile;

    private Company company;

    private List<Company> companys;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    //无参构造
    public UserDetailsImpl() {
    }

    //用User构造
    public UserDetailsImpl(Member member) {
        this.username = member.getUserName();
        this.password = member.getPassword();
    }

    //用User和List<Role>构造
    public UserDetailsImpl(Member user, List<Role> roles) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.roles = roles;
        this.mobile = user.getMobile();
    }

    public UserDetailsImpl(Member user, List<Role> roles, List<Company> companies) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.roles = roles;
        this.mobile = user.getMobile();
        this.companys = companies;
        if (companies != null && Objects.equals(companies.size() , 1)){
            company = companys.get(0);
        }
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getId().toString()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getCompanys() {
        return companys;
    }

    public void setCompanys(List<Company> companys) {
        this.companys = companys;
    }
}
