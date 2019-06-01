package com.xuecheng.userservice.repository;

import com.xuecheng.entities.system.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
}
