package com.bgur.mapper;

import com.bgur.pojo.WechatApp;

public interface WechatAppMapper {


    int insertSelective(WechatApp record);

    WechatApp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatApp record);

}