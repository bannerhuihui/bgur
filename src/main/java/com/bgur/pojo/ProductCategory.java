package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = -7479181717319121752L;
    /** 分类ID */
    private Integer id;
    /** 分类名称 */
    private String name;
    /** 上级分类ID */
    private Integer parentId;
    /** 所属公司ID */
    private Integer companyId;
    /** 排序权重 */
    private Integer sort;
    /** 状态 */
    private Integer status;
    /** 扩展字段 */
    private String extJson;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}