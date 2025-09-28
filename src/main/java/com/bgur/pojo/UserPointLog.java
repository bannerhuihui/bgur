package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPointLog implements Serializable {
    private static final long serialVersionUID = -1379783919801595195L;
    /** 主键ID */
    private Integer id;
    /** 用户ID */
    private Integer userId;
    /** 所属公司ID */
    private Integer companyId;
    /** 积分变动值（正负） */
    private Integer change;
    /** 变动类型（下单、兑换等） */
    private String type;
    /** 关联订单ID（可选） */
    private Integer orderId;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    private Date createTime;
}