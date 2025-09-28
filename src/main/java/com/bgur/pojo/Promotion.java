package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion implements Serializable {
    private static final long serialVersionUID = -4568130330168573859L;
    /** 主键ID */
    private Integer id;
    /** 所属公司ID */
    private Integer companyId;
    /** 活动名称 */
    private String name;
    /** 活动类型（满减、折扣等） */
    private String type;
    /** 开始时间 */
    private Date startTime;
    /** 结束时间 */
    private Date endTime;
    /** 活动规则（JSON） */
    private String ruleJson;
    /** 状态 */
    private Integer status;
    /** 每人限购 */
    private Integer limitPerUser;
    /** 总限量 */
    private Integer limitTotal;
    /** 活动图片 */
    private String bannerUrl;
    /** 扩展字段 */
    private String extJson;
    /** 创建时间 */
    private Date createTime;
}