package com.xuecheng.entities.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Role extends Model<Role> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    private String roleName;


    private Integer status;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
