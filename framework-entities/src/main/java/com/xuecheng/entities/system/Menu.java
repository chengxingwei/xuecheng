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
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("xc_menu")
public class Menu extends Model<Menu> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String menuName;

    private Long parentId;

    private String menuPath;

    private String btnID;

    private Integer menuType;

    private String btnName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
