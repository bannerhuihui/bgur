package com.bgur.mapper;

import com.bgur.pojo.Product;

public interface ProductMapper {


    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

}