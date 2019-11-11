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
@TableName("xc_role_menu")
public class RoleMenu extends Model<RoleMenu> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long roleID;

    private Long menuID;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
