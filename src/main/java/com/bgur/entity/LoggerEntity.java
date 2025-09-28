package com.bgur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: bgur
 * @package: com.bgur.entity
 * @className: LoggerEntity
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/16 18:30
 * @version: 1.0
 */
@Data
public class LoggerEntity implements Serializable {
    private static final long serialVersionUID = -5109736112437953916L;
    private String operId; // 主键ID

    private String operModule; // 功能模块

    private String operType; // 操作类型

    private String operDesc; // 操作描述

    private String operRequestParam; // 请求参数

    private String operResponseParam; // 返回参数

    private String operUserId; // 操作员ID

    private String operUserName; // 操作员名称

    private String operMethod; // 操作方法

    private String operUri; // 请求url

    private String operIp;// 请求IP地址

    private String operVer;// 操作版本号

    private String modelName; // 系统名称

    private Date createTime; //创建时间
}
