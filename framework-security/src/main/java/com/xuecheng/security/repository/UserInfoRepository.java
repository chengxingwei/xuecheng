package com.xuecheng.security.repository;

import com.xuecheng.entities.system.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    public UserInfo findByUserName(String username);
}
