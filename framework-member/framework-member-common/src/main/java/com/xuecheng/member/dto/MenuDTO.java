package com.xuecheng.member.dto;

import com.xuecheng.dto.BaseDTO;
import lombok.Data;

@Data
public class MenuDTO  extends BaseDTO {
    private Long id;
    private String menuName;

    private Long parentId;

    private String menuPath;

    private Integer menuType;

    private String btnName;

    /*
    角色id
     */
    private Long roleId;

    private Integer sort;

    private Long[] ids;
}
