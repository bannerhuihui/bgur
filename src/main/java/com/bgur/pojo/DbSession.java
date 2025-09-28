package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbSession implements Serializable {
    private static final long serialVersionUID = 4235287448289716280L;
    /** 主键ID */
    private Integer id;
    /** 用户ID */
    private Integer userId;
    /** 登录Token */
    private String token;
    /** 浏览器信息 */
    private String browser;
    /** 登录时间 */
    private Date loginTime;
    /** 过期时间 */
    private Date expireTime;
}