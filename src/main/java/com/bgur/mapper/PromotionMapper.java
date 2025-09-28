package com.bgur.mapper;

import com.bgur.pojo.Promotion;

public interface PromotionMapper {


    int insertSelective(Promotion record);

    Promotion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Promotion record);

}