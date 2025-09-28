package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {
    private static final long serialVersionUID = -2618026232860830572L;
    /** 主键ID */
    private Integer id;
    /** 订单ID */
    private Integer orderId;
    /** 商品ID */
    private Integer productId;
    /** 商品名称（快照） */
    private String productName;
    /** 商品单价（快照） */
    private Integer price;
    /** 购买数量 */
    private Integer quantity;
    /** 小计金额 */
    private Integer totalAmount;
    /** 创建时间 */
    private Date createTime;
    /** SKU ID（多规格） */
    private Integer skuId;
    /** 商品快照（JSON） */
    private String snapshotJson;
    /** 扩展字段 */
    private String extJson;
}