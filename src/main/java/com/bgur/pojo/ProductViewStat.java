package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewStat implements Serializable {
    private static final long serialVersionUID = -4632506865918940570L;
    /** 主键ID */
    private Integer id;
    /** 商品ID */
    private Integer productId;
    /** 用户ID */
    private Integer userId;
    /** 浏览次数 */
    private Integer viewCount;
    /** 最后一次浏览时间 */
    private Date lastViewTime;
}