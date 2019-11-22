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
@TableName("me_dept")
public class Dept extends Model<Dept> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 部门编号
     */
    @TableField("DeptCode")
    private String deptCode;

    /**
     * 部门名称
     */
    @TableField("DeptName")
    private String deptName;

    @TableField("ParentId")
    private Long parentId;

    /**
     * 父部门编号
     */
    @TableField("ParentCode")
    private String parentCode;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
