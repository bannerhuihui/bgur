package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = -8172362726645424988L;
    /** 订单ID */
    private Integer id;
    /** 所属公司ID */
    private Integer companyId;
    /** 下单用户ID */
    private Integer userId;
    /** 订单号 */
    private String orderNo;
    /** 订单总金额 */
    private Integer totalAmount;
    /** 订单状态 */
    private Integer status;
    /** 支付状态 */
    private Integer payStatus;
    /** 物流状态 */
    private Integer logisticsStatus;
    /** 收货地址ID */
    private Integer addressId;
    /** 下单时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 支付时间 */
    private Date payTime;
    /** 完成时间 */
    private Date finishTime;
    /** 取消时间 */
    private Date cancelTime;
    /** 支付方式 */
    private String payType;
    /** 备注 */
    private String remark;
    /** 扩展字段 */
    private String extJson;
    /** 软删除 */
    private Integer isDeleted;
    /** 创建人ID */
    private Integer createBy;
    /** 更新人ID */
    private Integer updateBy;
}