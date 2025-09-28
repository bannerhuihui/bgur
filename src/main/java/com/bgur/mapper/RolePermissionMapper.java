package com.bgur.mapper;

import com.bgur.pojo.RolePermission;

public interface RolePermissionMapper {


    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

}