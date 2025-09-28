package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -4345513696100666030L;
    /** 用户ID */
    private Integer id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 手机号 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 所属公司ID */
    private Integer companyId;
    /** 会员等级 */
    private Integer level;
    /** 邀请码 */
    private String inviteCode;
    /** 推荐人ID */
    private Integer referrerId;
    /** 状态 */
    private Integer status;
    /** 头像URL */
    private String avatar;
    /** 用户别名/昵称 */
    private String nickname;
    /** 性别（0未知/1男/2女） */
    private Integer gender;
    /** 生日 */
    private Date birthday;
    /** 最后登录时间 */
    private Date lastLoginTime;
    /** 最后登录IP */
    private String lastLoginIp;
    /** 注册时IP */
    private String registerIp;
    /** 注册来源（web/app/微信等） */
    private String source;
    /** 逻辑删除标记（0否/1是） */
    private Integer isDeleted;
    /** 备注 */
    private String remark;
    /** 扩展信息（JSON格式） */
    private String extJson;
    /** 连续登录失败次数 */
    private Integer loginFailCount;
    /** 账号锁定时间 */
    private Date lockTime;
    /** 邮箱是否已验证 */
    private Integer emailVerified;
    /** 手机号是否已验证 */
    private Integer phoneVerified;
    /** 创建时间 */
    private Date createTime;
}