package com.bgur.mapper;

import com.bgur.pojo.ProductCategory;

public interface ProductCategoryMapper {


    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductCategory record);

}