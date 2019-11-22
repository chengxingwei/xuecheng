package com.xuecheng.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author MinHow
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("me_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField("MenuName")
    private String menuName;

    /**
     * 菜单路径
     */
    @TableField("MenuPath")
    private String menuPath;

    /**
     * 父菜单ID
     */
    @TableField("ParentID")
    private Long parentID;

    /**
     * 1:菜单 2:按钮
     */
    @TableField("MenuType")
    private Integer menuType;

    @TableField("BtnName")
    private String btnName;

    @TableField("Sort")
    private Integer sort;

    @TableField("Company")
    private Long company;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
