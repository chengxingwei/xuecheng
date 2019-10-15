package com.xuecheng.security.dto;

import com.xuecheng.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class UserInfoDTO extends BaseDTO {

    private Long[] ids;

    private Long id;

    private String userName;


    private String password;


    private String mobile;

    private Integer status;


    private String nickName;

    private String realName;
}
