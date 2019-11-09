package com.xuecheng.security.dto;

import com.xuecheng.dto.BaseDTO;
import lombok.Data;

@Data
public class RoleDTO extends BaseDTO {
    private Long userId;
    private Long menuId;
    private Long id;
    private Long[] ids;
    private Integer status;
    private String roleName;
}
