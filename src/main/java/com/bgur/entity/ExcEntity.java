package com.bgur.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: bgur
 * @package: com.bgur.entity
 * @className: ExcEntity
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/16 18:42
 * @version: 1.0
 */
@Data
public class ExcEntity implements Serializable {

    private static final long serialVersionUID = -5498767431964980541L;

    private String excId; // 主键ID

    private String excRequestParam; // 请求参数

    private String excName; // 异常名称

    private String excMessage; // 异常信息

    private String operUserId; // 操作员ID

    private String operUserName; // 操作员名称

    private String operMethod;// 操作方法

    private String operUri; // 请求方法

    private String operIp; // 请求IP

    private String modelName; // 系统名称

    private Date createTime; //创建时间
}
