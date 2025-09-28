package com.bgur.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bgur.common.CommonEun;
import com.bgur.common.CommonResult;
import com.bgur.mapper.PermissionMapper;
import com.bgur.pojo.Permission;
import com.bgur.service.MenuService;
import com.bgur.util.SecurityUtils;
import com.bgur.vo.MenuTreeVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName: bgur
 * @package: com.bgur.service.impl
 * @className: MeunServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/17 10:18
 * @version: 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public CommonResult getCurrentUserMenuTree() {
        //获取用户ID
        Integer userId = SecurityUtils.getCurrentUserId();
        if(userId == null){
            return CommonResult.failure(CommonEun.USER_LOGIN_NOT_FOND);
        }
        List<Permission> permissions = permissionMapper.selectPermissionByUserId(userId);
        if(CollectionUtil.isEmpty(permissions)){
            return CommonResult.failure(CommonEun.TREE_PERMISSION_NULL);
        }
        // 假设你已经拿到 List<Permission> permissions
        List<MenuTreeVO> menuTree = buildMenuTree(permissions);
        List<String> buttonCodes = buildButtonPermissionCodes(permissions);
        Map res = new HashMap();
        res.put("menuTree",menuTree);
        res.put("buttonCodes",buttonCodes);
        return CommonResult.success(res);
    }


    public List<MenuTreeVO> buildMenuTree(List<Permission> permissions) {
        // 1. 过滤出菜单权限
        List<Permission> menuPermissions = permissions.stream()
                .filter(p -> "menu".equals(p.getType()))
                .collect(Collectors.toList());

        // 2. 构建 id -> MenuTreeVO 映射
        Map<Integer, MenuTreeVO> idMenuMap = new HashMap<>();
        for (Permission p : menuPermissions) {
            MenuTreeVO vo = new MenuTreeVO();
            vo.setId(p.getId());
            vo.setName(p.getName());
            vo.setPath(p.getPath());
            vo.setIcon(p.getIcon());
            vo.setSort(p.getSort());
            vo.setType(p.getType());
            vo.setCode(p.getCode());
            vo.setMeta(parseMeta(p.getMeta(), p));
            vo.setChildren(new ArrayList<>());
            vo.setHidden(false);
            vo.setComponent(p.getComponent());
            idMenuMap.put(p.getId(), vo);
        }

        // 3. 组装树结构
        List<MenuTreeVO> rootMenus = new ArrayList<>();
        for (MenuTreeVO vo : idMenuMap.values()) {
            Permission p = menuPermissions.stream().filter(x -> x.getId().equals(vo.getId())).findFirst().orElse(null);
            if (p != null && (p.getParentId() == null || p.getParentId() == 0)) {
                rootMenus.add(vo);
            } else if (p != null && idMenuMap.containsKey(p.getParentId())) {
                idMenuMap.get(p.getParentId()).getChildren().add(vo);
            }
        }

        // 4. 排序
        rootMenus.sort(Comparator.comparing(MenuTreeVO::getSort, Comparator.nullsLast(Comparator.naturalOrder())));
        for (MenuTreeVO vo : idMenuMap.values()) {
            if (vo.getChildren() != null) {
                vo.getChildren().sort(Comparator.comparing(MenuTreeVO::getSort, Comparator.nullsLast(Comparator.naturalOrder())));
            }
        }

        return rootMenus;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, Object> parseMeta(String metaJson, Permission p) {
        Map<String, Object> meta = new HashMap<>();
        try {
            if (metaJson != null && !metaJson.trim().isEmpty()) {
                meta = objectMapper.readValue(metaJson, Map.class);
            }
        } catch (Exception ignored) {}
        // 补充必要字段，防止前端缺失
        meta.putIfAbsent("title", p.getName());
        meta.putIfAbsent("icon", p.getIcon());
        meta.putIfAbsent("breadcrumb", true);
        return meta;
    }

    public List<String> buildButtonPermissionCodes(List<Permission> permissions) {
        return permissions.stream()
                .filter(p -> "button".equals(p.getType()))
                .map(Permission::getCode)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
