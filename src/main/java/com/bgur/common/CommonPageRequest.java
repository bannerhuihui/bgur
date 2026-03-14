package com.bgur.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * @projectName: bgur
 * @package: com.bgur.entity
 * @className: CommonPageRequestEntity
 * @author: huihui
 * @description: TODO
 * @date: 2025/10/21 19:56
 * @version: 1.0
 */
@Data
public class CommonPageRequest implements Serializable {

    private static final long serialVersionUID = 7579004640699773806L;

    /** 起始页 */
    private Integer currentPage;

    /** 起始索引值 */
    private Integer index;

    /** 每页数量 */
    private Integer maxRow;

    /** 开始时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 创建时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    /** 备注 */
    private String remark;

    /** 查询参数 */
    private HashMap<String,Object> args;
}
