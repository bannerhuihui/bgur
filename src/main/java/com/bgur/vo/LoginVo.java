package com.bgur.vo;

import com.bgur.pojo.User;
import lombok.Data;

/**
 * @projectName: bgur
 * @package: com.bgur.vo
 * @className: LoginVo
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/16 13:14
 * @version: 1.0
 */
@Data
public class LoginVo {

    private String accessToken;

    private String refreshToken;

    private User user;
}
