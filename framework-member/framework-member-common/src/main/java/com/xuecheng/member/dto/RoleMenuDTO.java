package com.xuecheng.member.dto;

import com.xuecheng.dto.BaseDTO;
import lombok.Data;

@Data
public class RoleMenuDTO extends BaseDTO {
    private String menuPath;
    private Long menuId;
}
