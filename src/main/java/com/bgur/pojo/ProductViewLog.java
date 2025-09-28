package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewLog implements Serializable {
    private static final long serialVersionUID = 686896943744623698L;
    /** 主键ID */
    private Integer id;
    /** 用户ID */
    private Integer userId;
    /** 商品ID */
    private Integer productId;
    /** 所属公司ID */
    private Integer companyId;
    /** 浏览时间 */
    private Date viewTime;
    /** 浏览IP */
    private String ip;
    /** 设备/终端信息 */
    private String device;
}