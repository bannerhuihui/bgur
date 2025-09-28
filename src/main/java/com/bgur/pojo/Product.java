package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = -3418690639380386853L;
    /** 商品ID */
    private Integer id;
    /** 所属公司ID */
    private Integer companyId;
    /** 商品名称 */
    private String name;
    /** 商品编码 */
    private String code;
    /** 售价 */
    private Integer price;
    /** 库存 */
    private Integer stock;
    /** 排序权重 */
    private Integer sort;
    /** 销量 */
    private Integer salesVolume;
    /** 好评率 */
    private Integer goodRate;
    /** 上下架状态 */
    private Integer status;
    /** 可见范围（JSON） */
    private String visibleScope;
    /** 商品描述 */
    private String description;
    /** 商品主图 */
    private String imageUrl;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 分类ID */
    private Integer categoryId;
    /** 品牌ID */
    private Integer brandId;
    /** 单位 */
    private String unit;
    /** 规格参数（JSON） */
    private String specJson;
    /** 原价 */
    private Integer originPrice;
    /** 扩展字段 */
    private String extJson;
    /** 软删除 */
    private Integer isDeleted;
    /** 创建人ID */
    private Integer createBy;
    /** 更新人ID */
    private Integer updateBy;
    /** 备注 */
    private String remark;
}