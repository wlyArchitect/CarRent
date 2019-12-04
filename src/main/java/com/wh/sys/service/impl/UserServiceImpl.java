package com.wh.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wh.sys.constant.SysConstant;
import com.wh.sys.entity.Role;
import com.wh.sys.entity.User;
import com.wh.sys.mapper.RoleMapper;
import com.wh.sys.mapper.UserMapper;
import com.wh.sys.service.UserService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.TreeNodeUtils;
import com.wh.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 万浩
 * @data 2019/11/15 20:28
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User login(UserVo userVo) {
        //明文123456
        //生成密文
        String psd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(psd);
        return userMapper.login(userVo);
    }

    /**
     * 查询所有的用户
     *
     * @param userVo
     * @return
     */
    @Override
    public DataGridViewUtils queryAllUser(UserVo userVo) {
        System.err.println(userVo);
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<User> data = this.userMapper.queryAllUser(userVo);
        DataGridViewUtils dataGridViewUtils = new DataGridViewUtils(page.getTotal(), data);
        return dataGridViewUtils;
    }

    /**
     * 添加操作
     *
     * @param userVo
     */
    @Override
    public void addUser(UserVo userVo) {
        // 设置默认密码 123456
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstant.USER_DEFAULT_PWD.getBytes()));
        // 设置用户类型 都是普通用户type=2
        userVo.setType(SysConstant.USER_TYPE_NORMAL);
        this.userMapper.insertSelective(userVo);
    }

    /**
     * 更新操作
     *
     * @param userVo
     */
    @Override
    public void updateUser(UserVo userVo) {
        this.userMapper.updateByPrimaryKeySelective(userVo);
    }

    /**
     * 根据用户id删除
     *
     * @param userid
     */
    @Override
    public void deleteUserByPrimaryKey(Integer userid) {
        // 删除用户
        this.userMapper.deleteByPrimaryKey(userid);
        // 根据用户id删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByUid(userid);

    }

    /**
     * 批量删除用户
     *
     * @param ids
     */
    @Override
    public void deleteBatchUser(Integer[] ids) {
        for (Integer id : ids) {
            deleteUserByPrimaryKey(id);
        }
    }

    /**
     * 重置密码,也就是设置为默认密码
     *
     * @param userid
     */
    @Override
    public void resetUserPwd(Integer userid) {
        User user = new User();
        user.setUserid(userid);
        //重置密码为默认密码123456
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstant.USER_DEFAULT_PWD.getBytes()));
        //更新(有选择的更新)
        this.userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 加载用户管理的角色数据
     * TODO 和当前角色初始化菜单项有些不一样
     * 这个是初始化表格,之前`角色初始化菜单是初始化树
     *
     * @param userid
     * @return
     */
    @Override
    public DataGridViewUtils queryUserRole(Integer userid) {
        //1.查询所有可用的角色
        Role role = new Role();
        //设置角色可用
        role.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Role> allRole = roleMapper.queryAllRole(role);
        //2.查询用户id对应的角色
        List<Role> userRole = roleMapper.queryRoleByUid(SysConstant.AVAILABLE_TRUE, userid);
        //接收返回的数据
        List<Map<String, Object>> data = new ArrayList<>();
        for (Role r1 : allRole) {
            //默认不选中
            Boolean LAY_CHECKED = false;
            for (Role r2 : userRole) {
                if (r1.getRoleid().equals(r2.getRoleid())) {
                    LAY_CHECKED = true;
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("roleid", r1.getRoleid());
            map.put("rolename", r1.getRolename());
            map.put("roledesc", r1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridViewUtils(data);
    }

    /**
     * 保存用户分配的角色
     *
     * @param userVo
     */
    @Override
    public void saveUserRole(UserVo userVo) {
        //获取用户的id
        Integer userid = userVo.getUserid();
        //获取角色的ids
        Integer[] roleIds = userVo.getIds();
        //根据用户id删除sys_role_user中的数据
        this.roleMapper.deleteRoleUserByUid(userid);
        //保存关系
        if (roleIds != null && roleIds.length > 0) {
            for (Integer rid : roleIds) {
                //保存用户和角色的关系
                this.userMapper.insertUserRole(userid, rid);
            }
        }
    }
}
