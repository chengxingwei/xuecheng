package com.xuecheng.member.dto;

import com.xuecheng.dto.BaseDTO;
import lombok.Data;

@Data
public class RoleDTO extends BaseDTO {
    private Long memberId;
    private Long menuId;
    private Long id;
    private Long[] ids;
    private Integer status;
    private String roleName;
    private Long[] menuIds;
}
