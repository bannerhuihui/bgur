package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 6948062898702016823L;
    /** 权限ID */
    private Integer id;
    /** 父节点ID */
    private Integer parentId;
    /** 权限名称 */
    private String name;
    /** 类型（菜单/按钮/接口） */
    private String type;
    /** 权限编码 */
    private String code;
    /** 路由路径 */
    private String path;
    /** 图标 */
    private String icon;
    /** 权限编码 */
    private Integer sort;
    /** 权限编码 */
    private String meta;
    /** 所属公司ID */
    private Integer companyId;
    /** 权限描述 */
    private String description;
    /** 组件路径 */
    private String component;
}