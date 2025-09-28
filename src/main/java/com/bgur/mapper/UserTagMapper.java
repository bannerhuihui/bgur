package com.bgur.mapper;

import com.bgur.pojo.UserTag;

public interface UserTagMapper {


    int insertSelective(UserTag record);

    UserTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTag record);

}