package com.bgur.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户扩展表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExtend implements Serializable {
    private static final long serialVersionUID = -5617896258881464539L;
    /** 主键ID */
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 所属公司ID */
    private Long companyId;

    /** 扩展字段名 */
    private String extKey;

    /** 扩展字段值 */
    private String extValue;

    /** 更新时间 */
    private Date updateTime;
}