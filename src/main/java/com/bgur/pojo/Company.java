package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
    private static final long serialVersionUID = 1084124144114700736L;
    /** 公司ID */
    private Integer id;
    /** 公司名称 */
    private String name;
    /** 状态 */
    private Integer status;
    /** 创建时间 */
    private Date createTime;
}