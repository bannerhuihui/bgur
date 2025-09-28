package com.bgur.mapper;

import com.bgur.pojo.Role;

import java.util.List;

public interface RoleMapper {


    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    List<Role> selectRoleByUserId(Integer id);
}