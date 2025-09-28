package com.bgur.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: bgur
 * @package: com.bgur.repository
 * @className: UserRepositoryTest
 * @author: huihui
 * @description: TODO
 * @date: 2025/6/26 14:55
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void passWordEncoder(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void saveUser(){

    }

}
