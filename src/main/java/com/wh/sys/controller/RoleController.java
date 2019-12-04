package com.wh.sys.controller;

import com.wh.sys.service.RoleService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.ResultObj;
import com.wh.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 万浩
 * @data 2019/11/26 10:31
 * @description
 */
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    /**
     * 查询 角色列表返回DataGridView
     *
     */
    @RequestMapping("/loadAllRole")
    public DataGridViewUtils loadAllRole(RoleVo roleVo) {
        return this.roleService.queryAllRole(roleVo);
    }

    /**
     * 添加角色
     */
    @RequestMapping("/addRole")
    public ResultObj addRole(RoleVo roleVo) {
        try {
            this.roleService.addRole(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改角色
     */
    @RequestMapping("/updateRole")
    public ResultObj updateRole(RoleVo roleVo) {
        try {
            this.roleService.updateRole(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResultObj deleteRole(RoleVo roleVo) {
        try {
            this.roleService.deleteRoleByPrimaryKey(roleVo.getRoleid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除角色,建立在删除角色之上
     */
    @RequestMapping("/deleteBatchRole")
    public ResultObj deleteBatchRole(RoleVo roleVo) {
        try {
            this.roleService.deleteBatchRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 加载当前角色 管理分配菜单的 json数据(二级json格式数据-->Object类型)
     */
    @RequestMapping("/initRoleMenuTreeJson")
    public DataGridViewUtils initRoleMenuTreeJson(Integer roleid) {
        return this.roleService.initRoleMenuTreeJson(roleid);
    }
    /**
     * 保存角色和菜单的关系
     */
    @RequestMapping("/saveRoleMenu")
    public ResultObj saveRoleMenu(RoleVo roleVo) {
        try {
            //保存角色与菜单的关系
            this.roleService.saveRoleMenu(roleVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

}