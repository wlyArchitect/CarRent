package com.wh.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wh.sys.constant.SysConstant;
import com.wh.sys.entity.Menu;
import com.wh.sys.entity.Role;
import com.wh.sys.mapper.MenuMapper;
import com.wh.sys.mapper.RoleMapper;
import com.wh.sys.service.RoleService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.TreeNodeUtils;
import com.wh.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 万浩
 * @data 2019/11/26 10:17
 * @description
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Role> queryAllRoleForList(RoleVo roleVo) {
        return roleMapper.queryAllRole(roleVo);
    }

    /**
     * 后期权限管理完成之后再来改
     */
    @Override
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return roleMapper.queryAllRole(roleVo);
    }

    @Override
    public DataGridViewUtils queryAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        List<Role> data = this.roleMapper.queryAllRole(roleVo);
        return new DataGridViewUtils(page.getTotal(), data);
    }

    @Override
    public void addRole(RoleVo roleVo) {
        this.roleMapper.insertSelective(roleVo);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        this.roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    /**
     * 删除sys_role角色表,通过id
     * 不仅要删除sys_role表,还有两张中间表sys_role_menu,sys_role_user表
     * @param roleid
     */
    @Override
    public void deleteRoleByPrimaryKey(Integer roleid) {
        // 删除sole角色表的数据
        this.roleMapper.deleteByPrimaryKey(roleid);
        // 根据角色id删除sys_role_menu里面的数据
        this.roleMapper.deleteRoleMenuByRid(roleid);
        // 根据角色id删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByRid(roleid);
    }

    /**
     * 批量删除,直接循环调用单个删除
     * @param ids
     */
    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer rid : ids) {
            deleteRoleByPrimaryKey(rid);
        }
    }

    /**
     * 初始化当前角色已分配的菜单项
     * @param roleid
     * @return
     */
    @Override
    public DataGridViewUtils initRoleMenuTreeJson(Integer roleid) {
        // 查询所有的可用的菜单
        Menu menu = new Menu();
        //设置菜单可用
        menu.setAvailable(SysConstant.AVAILABLE_TRUE);
        //所有的菜单项
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        //根据角色ID查询当前角色拥有的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(SysConstant.AVAILABLE_TRUE, roleid);
        //
        List<TreeNodeUtils> data = new ArrayList<>();
        //todo 两层for循环的作用就是为了t-ree展示 点击的sys_role表中的id展示它所拥有的菜单项
        for (Menu m1 : allMenu) {
            //默认就是不选中的
            String checkArr = SysConstant.CODE_ZERO+"";
            for (Menu m2 : roleMenu) {
                if (m1.getId().equals( m2.getId())) {
                    //如果所有的菜单项与当前角色id拥有的菜单的菜单项的id一致,说明被选中了
                    checkArr = SysConstant.CODE_ONE+"";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread().equals(SysConstant.SPREAD_TRUE)? true : false;
            data.add(new TreeNodeUtils(id, pid, title, spread, checkArr));
        }
        return new DataGridViewUtils(data);
    }

    /**
     * 保存角色分配的菜单
     * @param roleVo
     */
    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer rid=roleVo.getRoleid();
        Integer [] mids=roleVo.getIds();
        //根据rid删除sys_role_menu里面所有数据
        this.roleMapper.deleteRoleMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid : mids) {
            this.roleMapper.insertRoleMenu(rid,mid);
        }
    }
}
