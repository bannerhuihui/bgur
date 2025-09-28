package com.bgur.mapper;

import com.bgur.pojo.UserRole;

import java.util.List;

public interface UserRoleMapper {


    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    List<UserRole> selectRoleByUserId(Integer id);
}