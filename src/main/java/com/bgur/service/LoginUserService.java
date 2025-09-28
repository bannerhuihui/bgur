package com.bgur.service;

import com.bgur.bean.LoginUser;

public interface LoginUserService {
    LoginUser loadLoginUser(String userId);
}
