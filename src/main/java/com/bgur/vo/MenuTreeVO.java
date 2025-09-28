package com.bgur.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 菜单树VO
 * 用于前端展示的菜单结构
 */
@Data
public class MenuTreeVO implements Serializable {

    private static final long serialVersionUID = -4554580657886562954L;
    /**
     * 菜单ID
     */
    private Integer id;
    
    /**
     * 菜单名称
     */
    private String name;
    
    /**
     * 路由路径
     */
    private String path;
    
    /**
     * 图标
     */
    private String icon;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 类型（menu/button）
     */
    private String type;
    
    /**
     * 权限编码
     */
    private String code;
    
    /**
     * 元数据（JSON格式）
     */
    private Map<String, Object> meta;
    
    /**
     * 子菜单
     */
    private List<MenuTreeVO> children;
    
    /**
     * 是否隐藏
     */
    private Boolean hidden;
    
    /**
     * 重定向路径
     */
    private String redirect;
    
    /**
     * 组件路径
     */
    private String component;
} 