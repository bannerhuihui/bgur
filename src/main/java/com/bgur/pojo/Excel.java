package com.bgur.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Excel implements Serializable {

    private static final long serialVersionUID = -4681060229325997439L;

    private Integer id;

    private Integer userId;

    private Integer companyId;

    private String excelName;

    private Integer size;

    private Integer isAnalyzing;

    private String type;

    private Date createTime;

    private Date updateTime;

    private Integer success;

    private Integer error;

    private String data;

}