package com.bgur.bean;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @projectName: bgur
 * @package: com.bgur.bean
 * @className: LoginBean
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/16 15:10
 * @version: 1.0
 */
@Data
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 1608754409837588577L;
    private Integer id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

}
