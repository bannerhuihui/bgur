package com.bgur.mapper;

import com.bgur.pojo.SmsProviderConfig;

public interface SmsProviderConfigMapper {


    int insertSelective(SmsProviderConfig record);

    SmsProviderConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsProviderConfig record);

}