package com.bgur.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: bgur
 * @package: com.bgur.controller
 * @className: SSEController
 * @author: huihui
 * @description: 服务器推送事件控制器
 * @date: 2025/8/21 17:58
 * @version: 1.0
 */
@RestController
@RequestMapping("/chat")
@Tag(name = "实时通信", description = "服务器推送事件(SSE)和聊天相关接口")
public class SSEController {

    // TODO: 实现SSE相关功能

}
