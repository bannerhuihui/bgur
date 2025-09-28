package com.bgur.mapper;

import com.bgur.pojo.Order;

public interface OrderMapper {


    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

}