package com.bgur.mapper;

import com.bgur.pojo.UserAddress;

public interface UserAddressMapper {


    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAddress record);

}