package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress implements Serializable {
    private static final long serialVersionUID = 1998189073324043370L;
    /** 主键ID */
    private Integer id;
    /** 用户ID */
    private Integer userId;
    /** 所属公司ID */
    private Integer companyId;
    /** 收货人 */
    private String receiver;
    /** 联系电话 */
    private String phone;
    /** 省份 */
    private String province;
    /** 城市 */
    private String city;
    /** 区/县 */
    private String district;
    /** 详细地址 */
    private String address;
    /** 是否默认（0否/1是） */
    private Integer isDefault;
    /** 扩展字段 */
    private String extJson;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}