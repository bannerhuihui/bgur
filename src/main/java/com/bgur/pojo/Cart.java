package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    private static final long serialVersionUID = -4162965448606217493L;
    /** 主键ID */
    private Integer id;
    /** 用户ID */
    private Integer userId;
    /** 所属公司ID */
    private Integer companyId;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}