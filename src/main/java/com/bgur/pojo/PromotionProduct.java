package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionProduct implements Serializable {
    private static final long serialVersionUID = 8186888147458692790L;
    /** 主键ID */
    private Integer id;
    /** 活动ID */
    private Integer promotionId;
    /** 商品ID */
    private Integer productId;
    /** 创建时间 */
    private Date createTime;
}