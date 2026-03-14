package com.bgur.mongodb.bean;

import lombok.Data;

import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;

/**
 * @projectName: bgur
 * @package: com.bgur.mongodb.bean
 * @className: ExcelBean
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 21:27
 * @version: 1.0
 */
@Data
public class ExcelBean implements Serializable {

    private static final long serialVersionUID = -6378698298347944430L;

    private MultipartFile excelFile;

    private Integer userId;

    private Integer companyId;

    private String type;

}
