package com.xuecheng.entities.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Button extends Model<Button> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String btnName;


    private String btnClass;


    private String bgImage;


    private String bthStyle;


    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
