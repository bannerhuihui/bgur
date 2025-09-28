package com.bgur.mapper;

import com.bgur.pojo.UserPointAccount;

public interface UserPointAccountMapper {


    int insertSelective(UserPointAccount record);

    UserPointAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPointAccount record);

}