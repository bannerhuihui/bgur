package com.bgur.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户ID映射表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIdMapping implements Serializable {
    private static final long serialVersionUID = 8428308935502849798L;
    /** 主键ID */
    private Long id;

    /** 本地用户ID */
    private Long userId;

    /** 外部系统类型（如qywx等） */
    private String systemType;

    /** 外部系统用户唯一标识 */
    private String externalUserId;

    /** 创建时间 */
    private Date createTime;
}