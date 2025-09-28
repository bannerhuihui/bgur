package com.bgur.mapper;

import com.bgur.pojo.VerifyCode;

public interface VerifyCodeMapper {


    int insertSelective(VerifyCode record);

    VerifyCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VerifyCode record);

}