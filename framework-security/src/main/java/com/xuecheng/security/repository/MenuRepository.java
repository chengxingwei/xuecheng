package com.xuecheng.security.repository;

import com.xuecheng.entities.system.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    public Menu findByMenuPath(String menuPath);
}
