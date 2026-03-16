package com.bgur.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 3602067485009872724L;

    private List<T> list;
    private Integer total;
    private Integer page;
    private Integer pageSize;
    private Integer totalPage;
}

