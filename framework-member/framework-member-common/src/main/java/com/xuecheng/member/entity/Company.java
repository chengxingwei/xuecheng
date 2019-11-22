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
@TableName("me_company")
public class Company extends Model<Company> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

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

    /**
     * 企业名称
     */
    @TableField("CompanyName")
    private String companyName;

    /**
     * 税号
     */
    @TableField("DutySign")
    private String dutySign;

    /**
     * 企业法人
     */
    @TableField("LegalUserName")
    private String legalUserName;

    /**
     * 法人电话
     */
    @TableField("LegalUserMobile")
    private String legalUserMobile;

    /**
     * 联系人
     */
    @TableField("Contact")
    private String contact;

    /**
     * 联系电话
     */
    @TableField("Mobile")
    private String mobile;

    /**
     * 1:新注册 2:通过审核 3:审核失败 4：停用
     */
    @TableField("Status")
    private Integer status;

    /**
     * 状态错误描述
     */
    @TableField("StatusDesc")
    private String statusDesc;

    @TableField("RegistTime")
    private LocalDateTime registTime;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
