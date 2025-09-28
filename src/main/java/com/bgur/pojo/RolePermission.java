package com.bgur.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -3042862570963842494L;
    /** 主键ID */
    private Integer id;
    /** 角色ID */
    private Integer roleId;
    /** 权限ID */
    private Integer permissionId;
}