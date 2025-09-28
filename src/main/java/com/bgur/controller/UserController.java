package com.bgur.controller;

import com.bgur.common.CommonResult;
import com.bgur.log.OperLog;
import com.bgur.pojo.User;
import com.bgur.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: bgur
 * @package: com.bgur.controller
 * @className: UserController
 * @author: huihui
 * @description: 用户管理控制器
 * @date: 2025/7/15 15:00
 * @version: 1.0
 */
@RestController
@Tag(name = "用户管理", description = "用户登录、登出、Token刷新等相关接口")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户通过用户名和密码进行登录认证，成功后返回JWT Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "401", description = "用户名或密码错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @OperLog(operModule = "用户登录", operType = "查询操作", operDesc = "用户登录")
    public CommonResult login(
            @Parameter(description = "用户登录信息", required = true) @RequestBody User user, 
            HttpServletRequest request){
        return userService.login(user,request);
    }

    @GetMapping("/refreshToken")
    @Operation(summary = "刷新Token", description = "当访问Token过期时，使用刷新Token获取新的访问Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token刷新成功", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "401", description = "刷新Token无效或已过期"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @OperLog(operModule = "更换Token", operType = "查询操作", operDesc = "用户token过期后，重新获取新的token")
    public CommonResult refreshToken(
            @Parameter(description = "刷新Token", required = true) String refreshToken,
            HttpServletRequest request){
        return userService.refreshToken(refreshToken,request);
    }

    @GetMapping("/logout/{userId}")
    @Operation(summary = "用户登出", description = "用户主动登出系统，清除相关会话信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登出成功", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @OperLog(operModule = "退出登陆", operType = "退出登陆", operDesc = "用户手动退出登陆")
    public CommonResult logout(
            @Parameter(description = "用户ID", required = true) @PathVariable("userId") Integer userId){
        return userService.logout(userId);
    }
}
