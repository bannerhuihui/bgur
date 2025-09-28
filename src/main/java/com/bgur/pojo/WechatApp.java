package com.bgur.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 公众号/小程序基础信息表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WechatApp implements Serializable {
    private static final long serialVersionUID = 4642479804860593670L;
    /** 主键ID */
    private Long id;

    /** 所属公司ID */
    private Long companyId;

    /** 类型（公众号/小程序） */
    private String type;

    /** AppID */
    private String appid;

    /** AppSecret */
    private String secret;

    /** 名称 */
    private String name;

    /** 状态 */
    private Byte status;

    /** 微信支付公钥 */
    private String wxpayPublicKey;

    /** 微信支付私钥 */
    private String wxpayPrivateKey;

    /** 审核到期时间 */
    private Date auditExpireTime;

    /** 创建时间 */
    private Date createTime;
}