package com.bgur.common;

import lombok.Getter;

@Getter
public enum CommonEun {
    SUCCESS (2000, "请求成功"),
    ARGUMENT_NULL(4004,"请求参数为空"),

    USER_LOGIN_NOT_FOND(4001,"用户未找到"),

    USER_LOGIN_NAME_NULL(4002,"登录名为空"),

    USER_LOGIN_IS_DELETE(4003,"账号已经被移除" ),
    USER_LOGIN_FAIL_COUNT_MAX(4004 ,"连续登录失败次数过多，账号被锁定，请联系管理员" ),

    USER_LOGIN_EMAIL_FIELD(4005, "登录邮箱未验证"),

    USER_LOGIN_PHONE_FIELD(4006, "登录手机号未验证"),


    USER_LOGIN_PASSWORD_NULL(4007,"登录密码为空" ),

    USER_LOGIN_USER_EXCEPTION(4008,"账号异常" ),


    USER_LOGIN_PASSWORD_ERROR(4009,"登录密码错误" ),

    TREE_PERMISSION_NULL(4010,"权限列表为空" ),
    TOKEN_OUT_TIME(401,"Token无效或已过期" );

    private Integer code;

    private String msg;

    CommonEun(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
