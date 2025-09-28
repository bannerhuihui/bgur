package com.bgur.mapper;

import com.bgur.pojo.PromotionProduct;

public interface PromotionProductMapper {


    int insertSelective(PromotionProduct record);

    PromotionProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PromotionProduct record);

}