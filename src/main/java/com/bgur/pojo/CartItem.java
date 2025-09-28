package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable {
    private static final long serialVersionUID = -5250914918298918146L;
    /** 主键ID */
    private Integer id;
    /** 购物车ID */
    private Integer cartId;
    /** 商品ID */
    private Integer productId;
    /** 数量 */
    private Integer quantity;
    /** 是否选中（0/1） */
    private Integer selected;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}