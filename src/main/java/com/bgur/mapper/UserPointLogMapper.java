package com.bgur.mapper;

import com.bgur.pojo.UserPointLog;

public interface UserPointLogMapper {


    int insertSelective(UserPointLog record);

    UserPointLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPointLog record);

}