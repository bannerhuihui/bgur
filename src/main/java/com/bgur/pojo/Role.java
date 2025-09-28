package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = -6358267857285922071L;
    /** 角色ID */
    private Integer id;
    /** 角色名称 */
    private String name;
    /** 角色编号 */
    private String code;
    /** 所属公司ID */
    private Integer companyId;
    /** 角色描述 */
    private String description;
    /** 创建时间 */
    private Date createTime;

}