package com.bgur.mapper;

import com.bgur.pojo.UserIdMapping;

public interface UserIdMappingMapper {


    int insertSelective(UserIdMapping record);

    UserIdMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserIdMapping record);

}