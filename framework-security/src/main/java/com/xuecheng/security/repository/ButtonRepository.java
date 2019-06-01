package com.xuecheng.security.repository;

import com.xuecheng.entities.system.Button;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ButtonRepository extends JpaRepository<Button,Long> {
}
