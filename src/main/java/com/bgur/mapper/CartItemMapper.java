package com.bgur.mapper;

import com.bgur.pojo.CartItem;

public interface CartItemMapper {


    int insertSelective(CartItem record);

    CartItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CartItem record);

}