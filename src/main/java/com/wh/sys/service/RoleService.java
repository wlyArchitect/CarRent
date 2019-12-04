package com.wh.sys.service;

import com.wh.sys.entity.Role;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.vo.RoleVo;

import java.util.List;

/**
 * 角色服务层接口
 * @author 万浩
 * @data 2019/11/26 9:29
 * @description
 */
public interface RoleService {
    /**
     * 查询所有角色返回
     * List<Role>
     */
    public List<Role> queryAllRoleForList(RoleVo roleVo);

    /**
     * 根据用户id 查询 用户的可用角色,从而找到可以可用菜单项
     */
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo,Integer userId);

    /**
     * 查询所有角色
     * @param roleVo
     * @return
     */
    public DataGridViewUtils queryAllRole(RoleVo roleVo);

    /**
     * 添加角色
     * @param roleVo
     */
    public void addRole(RoleVo roleVo);

    /**
     * 修改角色
     * @param roleVo
     */
    public void updateRole(RoleVo roleVo);

    /**
     * 根据id删除角色
     * @param roleid
     */
    public void deleteRoleByPrimaryKey(Integer roleid);
    /**
     * 批量删除角色
     * @param ids
     */
    public void deleteBatchRole(Integer [] ids);

    /**
     * 初始化当前角色已分配的菜单
     * @param roleid
     * @return
     */
    DataGridViewUtils initRoleMenuTreeJson(Integer roleid);

    /**
     * 保存角色分配的菜单
     * @param roleVo
     */
    void saveRoleMenu(RoleVo roleVo);
}
