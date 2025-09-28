package com.bgur.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信服务商配置表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsProviderConfig implements Serializable {
    private static final long serialVersionUID = 5292683594495605242L;
    /** 主键ID */
    private Long id;

    /** 所属公司ID */
    private Long companyId;

    /** 服务商类型（aliyun/cmcc等） */
    private String provider;

    /** API Key/AccessKey */
    private String accessKey;

    /** API Secret/AccessSecret */
    private String secretKey;

    /** 短信签名 */
    private String signName;

    /** 其他扩展参数（JSON） */
    private String extJson;

    /** 状态（0禁用/1启用） */
    private Byte status;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}