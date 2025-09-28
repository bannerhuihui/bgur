package com.bgur.mapper;

import com.bgur.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    User login(@Param("loginName") String loginName);

}