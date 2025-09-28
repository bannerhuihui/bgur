package com.bgur.mapper;

import com.bgur.pojo.Permission;

import java.util.List;

public interface PermissionMapper {


    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    List<Permission> selectPermissionByUserId(Integer id);
}