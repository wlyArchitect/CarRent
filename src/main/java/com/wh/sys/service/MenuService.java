package com.wh.sys.service;

import com.wh.sys.entity.Menu;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.vo.MenuVo;
import com.wh.sys.utils.ResultObj;

import java.util.List;

/**
 * 菜单管理的service接口
 *
 * @author 万浩
 * @data 2019/11/16 20:48
 * @description
 */
public interface MenuService {
    /**
     * 超级管理员
     * 查询所有菜单返回(只有超级管理员才能全看到)
     * @param menuVo
     */
    List<Menu> queryAllMenuForList(MenuVo menuVo);
    /**
     * 非超级管理员查看到的菜单项
     * 根据用户id查询用户的可用菜单(权限不同看到的界面不同)
     * @param menuVo
     * @param userId
     *
     */
    List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);

    /**
     * 查询所有的菜单项
     * @param menuVo
     */
    DataGridViewUtils queryAllMenu(MenuVo menuVo);

    /**
     * 添加菜单项
     * @param menuVo
     */
    void addMenu(MenuVo menuVo);

    /**
     * 修改菜单项
     * @param menuVo
     */
    void updateMenu(MenuVo menuVo);

    /**
     * 查询该菜单项 有几个子节点
     * @param id
     * @return
     */
    Integer queryMenuByPid(Integer id);

    /**
     * 删除菜单项:
     *  需要删除菜单表里的数据
     *  以及sys_role_menu表里的数据
     * @param id
     */
    void deleteMenuByPrimaryKey(Integer id);
    /**
     * 删除菜单项
     * @param mid
     */
    void deleteRoleMenuByMid(Integer mid);

}
