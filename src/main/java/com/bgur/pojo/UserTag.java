package com.bgur.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户标签表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTag implements Serializable {
    private static final long serialVersionUID = -2913900124683856657L;
    /** 主键ID */
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 所属公司ID */
    private Long companyId;

    /** 标签 */
    private String tag;

    /** 创建时间 */
    private Date createTime;
}