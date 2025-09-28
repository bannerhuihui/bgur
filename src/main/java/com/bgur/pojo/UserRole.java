package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 4113734248863604913L;
    /** 主键ID */
    private Integer id;
    /** 用户ID */
    private Integer userId;
    /** 角色ID */
    private Integer roleId;
}