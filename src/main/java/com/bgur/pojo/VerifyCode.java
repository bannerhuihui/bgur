package com.bgur.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码记录表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCode implements Serializable {
    private static final long serialVersionUID = 5244086349111891089L;
    /** 主键ID */
    private Long id;

    /** 用户ID（可选） */
    private Long userId;

    /** 类型（email/phone） */
    private String type;

    /** 目标（邮箱/手机号） */
    private String target;

    /** 验证码内容 */
    private String code;

    /** 状态（0未验证/1已验证/2已失效） */
    private Byte status;

    /** 发送时间 */
    private Date sendTime;

    /** 过期时间 */
    private Date expireTime;

    /** 验证通过时间 */
    private Date verifyTime;

    /** 发送/验证IP */
    private String ip;

    /** 验证失败次数 */
    private Byte failCount;

    /** 备注 */
    private String remark;
}