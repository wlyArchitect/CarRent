package com.wh.sys.service;

import com.wh.sys.entity.User;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.vo.UserVo;

/**
 * @author 万浩
 * @data 2019/11/15 20:28
 * @description
 */
public interface UserService {
    /**
     * 用户登录
     * @param userVo
     * @return
     */
    User login(UserVo userVo);
    /**
     * 查询所有角色
     * @param userVo
     * @return
     */
     DataGridViewUtils queryAllUser(UserVo userVo);

    /**
     * 添加角色
     * @param userVo
     */
    public void addUser(UserVo userVo);

    /**
     * 修改角色
     * @param userVo
     */
    public void updateUser(UserVo userVo);

    /**
     * 根据id删除角色
     * @param userid
     */
    public void deleteUserByPrimaryKey(Integer userid);
    /**
     * 批量删除角色
     * @param ids
     */
    public void deleteBatchUser(Integer [] ids);

    /**
     * 重置密码,通过用户id重置密码
     * @param userid
     */
    public void resetUserPwd(Integer userid);

    /**
     * 加载用户管理的分配角色的数据
     * @param userid
     * @return
     */
    DataGridViewUtils queryUserRole(Integer userid);

    /**
     * 保存用户分配的角色权限
     * @param userVo
     */
    void saveUserRole(UserVo userVo);
}
