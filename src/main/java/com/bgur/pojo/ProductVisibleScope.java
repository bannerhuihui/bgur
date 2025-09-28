package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVisibleScope implements Serializable {
    private static final long serialVersionUID = 3996731906752851349L;
    /** 主键ID */
    private Integer id;
    /** 商品ID */
    private Integer productId;
    /** 可见公司ID（可选） */
    private Integer companyId;
    /** 可见部门ID（可选） */
    private Integer departmentId;
    /** 可见用户标签（可选） */
    private String userTag;
    /** 可见用户ID（可选） */
    private Integer userId;
    /** 创建时间 */
    private Date createTime;
}