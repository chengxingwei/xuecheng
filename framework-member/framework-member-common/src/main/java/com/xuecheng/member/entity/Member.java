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
import java.time.LocalDateTime;
import java.util.Date;

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
@TableName("me_member")
public class Member extends Model<Member> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("UserName")
    private String userName;

    @TableField("Password")
    private String password;

    /**
     * 1:新注册 2:审核通过 3：审核不通过 4：停用
     */
    @TableField("Status")
    private Integer status;

    @TableField("RealName")
    private String realName;

    @TableField("NickName")
    private String nickName;

    /**
     * 头像
     */
    @TableField("Avatar")
    private String avatar;

    /**
     * 手机号
     */
    @TableField("Mobile")
    private String mobile;

    @TableField("ProvinceCode")
    private String provinceCode;

    @TableField("CityCode")
    private String cityCode;

    @TableField("DistrictCode")
    private String districtCode;

    @TableField("TownCode")
    private String townCode;

    @TableField("ProvinceName")
    private String provinceName;

    @TableField("CityName")
    private String cityName;

    @TableField("DistrictName")
    private String districtName;

    @TableField("TownName")
    private String townName;

    @TableField("VillageCode")
    private String villageCode;

    @TableField("VillageName")
    private String villageName;

    @TableField("AddressDetail")
    private String addressDetail;

    @TableField("QQ")
    private String qq;

    @TableField("Email")
    private String email;

    @TableField("RegistTime")
    private Date registTime;

    @TableField("StatusDesc")
    private String statusDesc;

    @Override
    protected Serializable pkVal() {
        return id;
    }

}
