package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logistics implements Serializable {
    private static final long serialVersionUID = -7299816702636539410L;
    /** 主键ID */
    private Integer id;
    /** 订单ID */
    private Integer orderId;
    /** 所属公司ID */
    private Integer companyId;
    /** 物流公司 */
    private String logisticsCompany;
    /** 物流单号 */
    private String logisticsNo;
    /** 物流状态 */
    private Integer status;
    /** 物流轨迹（JSON） */
    private String traceJson;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}