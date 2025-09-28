package com.bgur.mapper;

import com.bgur.pojo.OrderItem;

public interface OrderItemMapper {


    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

}