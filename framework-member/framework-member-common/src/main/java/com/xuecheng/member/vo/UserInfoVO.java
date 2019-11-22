package com.xuecheng.member.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserInfoVO implements Serializable {
    private Long id;

    private String userName;

    private String password;

    private String mobile;

    private Integer status;

    private String nickName;

    private String realName;

    private String roleNames;
}
