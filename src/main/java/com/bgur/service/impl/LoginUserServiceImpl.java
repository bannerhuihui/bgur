package com.bgur.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.bgur.bean.LoginUser;
import com.bgur.mapper.*;
import com.bgur.pojo.*;
import com.bgur.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @projectName: bgur
 * @package: com.bgur.service.impl
 * @className: LoginUserServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/16 15:31
 * @version: 1.0
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public LoginUser loadLoginUser(String userId) {
        if (StrUtil.isEmpty(userId)) {
            return null;
        }

        Integer id = Integer.valueOf(userId);
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setId(id);
        loginUser.setUsername(user.getUsername());
        loginUser.setPassword(user.getPassword());
        boolean enabled = user.getStatus() != null && user.getStatus() == 1;
        loginUser.setEnabled(enabled);
        loginUser.setAccountNonExpired(enabled);
        loginUser.setAccountNonLocked(enabled);
        loginUser.setCredentialsNonExpired(enabled);
        //根据用户ID获取用户角色
        List<Role> roles = roleMapper.selectRoleByUserId(id);
        //根据用户ID获取权限信息
        List<Permission> permissions = permissionMapper.selectPermissionByUserId(id);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roles)) {
            for (Role role : roles
            ) {
                if (role != null && StrUtil.isNotEmpty(role.getCode())) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
                }
            }
        }
        if (CollectionUtil.isNotEmpty(permissions)) {
            for (Permission per : permissions
            ) {
                if (per != null && StrUtil.isNotEmpty(per.getCode())) {
                    authorities.add(new SimpleGrantedAuthority(per.getCode()));
                }
            }
        }
        loginUser.setAuthorities(authorities);
        return loginUser;
    }
}
