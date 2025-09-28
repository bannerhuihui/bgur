package com.bgur.mapper;

import com.bgur.pojo.UserExtend;

public interface UserExtendMapper {


    int insertSelective(UserExtend record);

    UserExtend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserExtend record);

}