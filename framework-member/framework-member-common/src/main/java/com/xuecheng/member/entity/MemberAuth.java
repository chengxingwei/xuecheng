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
@TableName("me_member_auth")
public class MemberAuth extends Model<MemberAuth> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("AuthTypeId")
    private Long authTypeId;

    @TableField("AuthTypeCode")
    private String authTypeCode;

    @TableField("FrontImagePath")
    private String frontImagePath;

    @TableField("BackImagePath")
    private String backImagePath;

    @TableField("FrontImageDesc")
    private String frontImageDesc;

    @TableField("BackImageDesc")
    private String backImageDesc;

    /**
     * 1:单面 2:正反面
     */
    @TableField("Type")
    private Integer type;

    /**
     * 1:新提交 2：通过 3：不通过
     */
    @TableField("Status")
    private Integer status;

    @TableField("StatusDesc")
    private String statusDesc;

    @Override
    protected Serializable pkVal() {
        return id;
    }

}
