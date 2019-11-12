package com.xuecheng.security.dto;

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

    private Integer sort;

    private Long[] ids;
}
