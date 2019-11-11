package com.xuecheng.entities.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("xc_userinfo")
public class UserInfo extends Model<UserInfo> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userName;

    private String password;

    private String mobile;

    private Integer status;

    private String nickName;


    private String realName;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
