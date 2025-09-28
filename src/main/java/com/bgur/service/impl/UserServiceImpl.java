package com.bgur.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bgur.common.CommonEun;
import com.bgur.common.CommonResult;
import com.bgur.mapper.DbSessionMapper;
import com.bgur.mapper.UserMapper;
import com.bgur.pojo.DbSession;
import com.bgur.pojo.User;
import com.bgur.service.UserService;
import com.bgur.util.JwtUtil;
import com.bgur.util.RequestUtil;
import com.bgur.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: bgur
 * @package: com.bgur.service.impl
 * @className: UserServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/8 13:28
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DbSessionMapper dbSessionMapper;

    @Override
    public CommonResult login(User user, HttpServletRequest request) {
        if(user == null){
            return CommonResult.failure();
        }
        //判断用户是不是存在(用户名、手机号、邮箱)
        if(StrUtil.isEmpty(user.getUsername())){
            return CommonResult.failure(CommonEun.USER_LOGIN_NAME_NULL);
        }
        if(StrUtil.isEmpty(user.getPassword())){
            return CommonResult.failure(CommonEun.USER_LOGIN_PASSWORD_NULL);
        }
        User checkLoginUser = userMapper.login(user.getUsername());
        if(checkLoginUser == null){
            return CommonResult.failure(CommonEun.USER_LOGIN_NOT_FOND);
        }
        if(checkLoginUser.getIsDeleted() != null && checkLoginUser.getIsDeleted() == 1){
            return CommonResult.failure(CommonEun.USER_LOGIN_IS_DELETE);
        }
        if(checkLoginUser.getLoginFailCount() != null && checkLoginUser.getLoginFailCount() > 3){
            return CommonResult.failure(CommonEun.USER_LOGIN_FAIL_COUNT_MAX);
        }
        if(StrUtil.equals(user.getUsername(),checkLoginUser.getEmail()) && (checkLoginUser.getEmailVerified() != null && checkLoginUser.getEmailVerified() == 0)){
            return CommonResult.failure(CommonEun.USER_LOGIN_EMAIL_FIELD);
        }
        if(StrUtil.equals(user.getUsername(),checkLoginUser.getPhone()) && (checkLoginUser.getPhoneVerified() != null && checkLoginUser.getPhoneVerified() == 0)){
            return CommonResult.failure(CommonEun.USER_LOGIN_PHONE_FIELD);
        }
        if(checkLoginUser.getStatus() != null && checkLoginUser.getStatus() != 1){
            return CommonResult.failure(CommonEun.USER_LOGIN_USER_EXCEPTION);
        }
        //到此判断结束、进行密码比较
        boolean matches = passwordEncoder.matches(user.getPassword(), checkLoginUser.getPassword());
        if(matches){ //密码比对正确
            //1。修改最后登录时间
            checkLoginUser.setLastLoginTime(new Date());
            //2。最后的登录IP地址
            checkLoginUser.setLastLoginIp(RequestUtil.getClientIp(request));
            //修改登录失败次数为0
            checkLoginUser.setLoginFailCount(0);
            //3。生成统一token
            Map<String,Object> claims = new HashMap<>();
            claims.put("username",user.getUsername());
            claims.put("nickname",checkLoginUser.getNickname());
            String accessToken = JwtUtil.generateAccessToken(checkLoginUser.getId(),claims);
            String refreshToken = JwtUtil.generateRefreshToken(checkLoginUser.getId());
            //修改需要更新的内容 1。更细用户表信息 2。更新session表信息
            userMapper.updateByPrimaryKeySelective(checkLoginUser);
            DbSession dbSession = new DbSession();
            dbSession.setUserId(checkLoginUser.getId());
            dbSession.setLoginTime(checkLoginUser.getLastLoginTime());
            dbSession.setToken(refreshToken);
            dbSession.setBrowser(request.getHeader("User-Agent"));
            dbSession.setExpireTime(DateUtil.offsetDay(checkLoginUser.getLastLoginTime(), 7));
            dbSessionMapper.insertSelective(dbSession);
            LoginVo loginVo = new LoginVo();
            loginVo.setAccessToken(accessToken);
            loginVo.setRefreshToken(refreshToken);
            //返回的用户信息拼装
            User resUser = new User();
            resUser.setId(checkLoginUser.getId());
            resUser.setNickname(checkLoginUser.getNickname());
            resUser.setUsername(checkLoginUser.getUsername());
            resUser.setPhone(checkLoginUser.getPhone());
            resUser.setEmail(checkLoginUser.getEmail());
            resUser.setPhoneVerified(checkLoginUser.getPhoneVerified());
            resUser.setEmailVerified(checkLoginUser.getEmailVerified());
            resUser.setLastLoginTime(checkLoginUser.getLastLoginTime());
            resUser.setLevel(checkLoginUser.getLevel());
            resUser.setAvatar(checkLoginUser.getAvatar());
            //4。返回对应的数据给前端
            loginVo.setUser(resUser);
            return CommonResult.success(loginVo);
        }else{ //密码比对失败
            // 登录失败，失败次数+1
            int failCount = checkLoginUser.getLoginFailCount() == null ? 1 : checkLoginUser.getLoginFailCount() + 1;
            checkLoginUser.setLoginFailCount(failCount);
            if (failCount >= 3) {
                checkLoginUser.setLockTime(new Date());
                checkLoginUser.setStatus(0); // 锁定
            }
            userMapper.updateByPrimaryKeySelective(checkLoginUser);
            return CommonResult.failure(CommonEun.USER_LOGIN_PASSWORD_ERROR);
        }
    }

    @Override
    public CommonResult refreshToken(String refreshToken,HttpServletRequest request) {
        if(StrUtil.isEmpty(refreshToken)){
            return CommonResult.failure();
        }
        DbSession dbSession = dbSessionMapper.selectByRefreshToken(refreshToken);
        if(dbSession == null){
            return CommonResult.failure(CommonEun.TOKEN_OUT_TIME);
        }
        Integer userId = JwtUtil.getUserIdFromToken(refreshToken);
        if(userId == null || userId == 0){
            return CommonResult.failure(CommonEun.TOKEN_OUT_TIME);
        }
        //获取用户信息
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return CommonResult.failure(CommonEun.USER_LOGIN_NOT_FOND);
        }
        if(user.getIsDeleted() != null && user.getIsDeleted() == 1){
            return CommonResult.failure(CommonEun.USER_LOGIN_IS_DELETE);
        }
        if(user.getLoginFailCount() != null && user.getLoginFailCount() > 3){
            return CommonResult.failure(CommonEun.USER_LOGIN_FAIL_COUNT_MAX);
        }
        if(user.getStatus() != null && user.getStatus() != 1){
            return CommonResult.failure(CommonEun.USER_LOGIN_USER_EXCEPTION);
        }
        //1。修改最后登录时间
        user.setLastLoginTime(new Date());
        //2。最后的登录IP地址
        user.setLastLoginIp(RequestUtil.getClientIp(request));
        //3。生成统一token
        Map<String,Object> claims = new HashMap<>();
        claims.put("username",user.getUsername());
        claims.put("nickname",user.getNickname());
        String accessToken = JwtUtil.generateAccessToken(userId,claims);
        //返回的用户信息拼装
        User resUser = new User();
        resUser.setId(user.getId());
        resUser.setNickname(user.getNickname());
        resUser.setUsername(user.getUsername());
        resUser.setPhone(user.getPhone());
        resUser.setEmail(user.getEmail());
        resUser.setPhoneVerified(user.getPhoneVerified());
        resUser.setEmailVerified(user.getEmailVerified());
        resUser.setLastLoginTime(user.getLastLoginTime());
        resUser.setLevel(user.getLevel());
        resUser.setAvatar(user.getAvatar());
        LoginVo loginVo = new LoginVo();
        loginVo.setAccessToken(accessToken);
        loginVo.setUser(resUser);
        return CommonResult.success(loginVo);
    }

    @Override
    public CommonResult logout(Integer userId) {
        if(userId != null && userId !=0){
            // 用户退出登陆
            User user = userMapper.selectByPrimaryKey(userId);
            if(user != null){
                user.setLastLoginTime(new Date());
                userMapper.updateByPrimaryKeySelective(user);
                return CommonResult.success();
            }
            return CommonResult.failure(CommonEun.USER_LOGIN_NOT_FOND);
        }
        return CommonResult.failure();
    }
}
