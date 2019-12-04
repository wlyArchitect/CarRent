package com.wh.sys.controller;

import com.wh.sys.service.UserService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.ResultObj;
import com.wh.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author 万浩
 * @data 2019/11/15 20:30
 * @description
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 加载角色列表返回DataGridView
     */
    @RequestMapping("/loadAllUser")
    public DataGridViewUtils loadAllUser(UserVo userVo) {
        return this.userService.queryAllUser(userVo);
    }

    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public ResultObj addUser(UserVo userVo) {
        try {
            this.userService.addUser(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     */
    @RequestMapping("/updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            this.userService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     */
    @RequestMapping("/deleteUser")
    public @ResponseBody
    ResultObj deleteUser(UserVo userVo) {
        try {
            this.userService.deleteUserByPrimaryKey(userVo.getUserid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除用户,建立在删除用户之上
     */
    @RequestMapping("/deleteBatchUser")
    public @ResponseBody
    ResultObj deleteBatchUser(UserVo userVo) {
        try {
            this.userService.deleteBatchUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 重置密码
     */
    @RequestMapping("/resetUserPwd")
    public ResultObj resetUserPwd(Integer userid) {
        try {
            this.userService.resetUserPwd(userid);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 初始化用户分配的角色数据
     * 加载用户管理的分配角色的json数据(用户已分配的角色)
     */
    @RequestMapping("/initUserRole")
    public DataGridViewUtils initUserRole(UserVo userVo) {
        return this.userService.queryUserRole(userVo.getUserid());
    }

    /**
     * 保存用户和角色的关系
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(UserVo userVo) {
        try {
            this.userService.saveUserRole(userVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}