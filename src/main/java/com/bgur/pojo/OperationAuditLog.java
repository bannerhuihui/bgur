package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationAuditLog implements Serializable {
    private static final long serialVersionUID = -6869744785750655541L;
    /** 主键ID */
    private Integer id;
    /** 操作人ID */
    private Integer userId;
    /** 所属公司ID */
    private Integer companyId;
    /** 操作类型 */
    private String operationType;
    /** 操作内容 */
    private String content;
    /** 操作前数据快照 */
    private String beforeData;
    /** 操作后数据快照 */
    private String afterData;
    /** 操作IP */
    private String ip;
    /** 设备/终端 */
    private String device;
    /** 操作时间 */
    private Date createTime;
}