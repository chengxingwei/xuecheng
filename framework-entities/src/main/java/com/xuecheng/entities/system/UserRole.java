package com.xuecheng.entities.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("xc_user_role")
public class UserRole extends Model<UserRole> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userInfoID;

    private Long roleID;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
