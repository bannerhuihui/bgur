package com.bgur.controller;

import com.bgur.common.CommonPageRequest;
import com.bgur.common.CommonResult;
import com.bgur.log.OperLog;
import com.bgur.mongodb.bean.ExcelBean;
import com.bgur.service.ExcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: bgur
 * @package: com.bgur.controller
 * @className: ExcelController
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 20:08
 * @version: 1.0
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;


    @Operation(summary = "获取公司对应的excel上传信息", description = "根据条件查询用户的excel信息列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "401", description = "未授权访问"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @SecurityRequirement(name = "Bearer")
    @OperLog(operModule = "获取用户对应的公司excel信息列表", operType = "获取excel列表", operDesc = "根据用户信息获取对应的excel列表")
    @PostMapping("/select/limit")
    public CommonResult getExcelList(@Parameter CommonPageRequest commonPageRequest){
        return excelService.getExcelList(commonPageRequest);
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    @OperLog(operModule = "excel上传", operType = "上传", operDesc = "excel上传解析操作")
    public CommonResult uploadExcel(@ModelAttribute ExcelBean excelBean){
        return excelService.uploadExcel(excelBean);
    }

}
