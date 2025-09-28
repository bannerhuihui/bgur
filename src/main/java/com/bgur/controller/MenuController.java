package com.bgur.controller;

import com.bgur.common.CommonResult;
import com.bgur.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: bgur
 * @package: com.bgur.controller
 * @className: MenuController
 * @author: huihui
 * @description: 菜单管理控制器
 * @date: 2025/7/17 10:16
 * @version: 1.0
 */
@RestController
@Tag(name = "菜单管理", description = "用户菜单权限相关接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/tree")
    @Operation(summary = "获取当前用户菜单树", description = "根据当前登录用户的权限获取可访问的菜单树结构")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "401", description = "未授权访问"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @SecurityRequirement(name = "Bearer")
    public CommonResult getCurrentUserMenuTree(){
        return menuService.getCurrentUserMenuTree();
    }
}
