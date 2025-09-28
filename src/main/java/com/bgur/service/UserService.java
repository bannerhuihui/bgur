package com.bgur.service;

import com.bgur.common.CommonResult;
import com.bgur.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    CommonResult login(User user, HttpServletRequest request);

    CommonResult refreshToken(String refreshToken,HttpServletRequest request);

    CommonResult logout(Integer userId);

}
