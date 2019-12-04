package com.wh.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wh.sys.entity.Menu;
import com.wh.sys.mapper.MenuMapper;
import com.wh.sys.service.MenuService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单项的service层
 * @author 万浩
 * @data 2019/11/16 20:48
 * @description
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有的菜单项
     * @param menuVo
     * @return
     */
    @Override
    public List<Menu> queryAllMenuForList(MenuVo menuVo) {
        return menuMapper.queryAllMenu(menuVo);
    }

    /**
     * (后期权限管理完成后进行修改)
     * 对于不同的用户登录查看的菜单项不同
     * @param menuVo
     * @param userId
     * @return
     */
    @Override
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
        return menuMapper.queryMenuByUid(menuVo.getAvailable(),userId);
    }

    /**
     * 查询所有的菜单项
     * @param menuVo
     * @return
     */
    @Override
    public DataGridViewUtils queryAllMenu(MenuVo menuVo) {
        //使用PageHelper分页插件
        Page<Object> page = PageHelper.startPage(menuVo.getPage(), menuVo.getLimit());
        List<Menu> data = this.menuMapper.queryAllMenu(menuVo);
        return new DataGridViewUtils(page.getTotal(), data);
    }

    /**
     * 添加菜单项
     * @param menuVo
     * @return
     */
    @Override
    public void addMenu(MenuVo menuVo) {
            this.menuMapper.insert(menuVo);
    }

    /**
     * 修改菜单项
     * @param menuVo
     */
    @Override
    public void updateMenu(MenuVo menuVo) {
        /** 有选择性的修改 */
        this.menuMapper.updateByPrimaryKeySelective(menuVo);
    }
    /**
       查询菜单项的子节点个数
     */
    @Override
    public Integer queryMenuByPid(Integer id) {
        return this.menuMapper.queryMenuByPid(id);
    }

    /**
     * 删除菜单项:
     *   删除菜单表里的菜单项
     *   以及sys_role_menu表里的菜单项
     * @param id
     */
    @Override
    public void deleteMenuByPrimaryKey(Integer id) {
         this.menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteRoleMenuByMid(Integer mid) {
        this.menuMapper.deleteRoleMenuByMid(mid);
    }

}
