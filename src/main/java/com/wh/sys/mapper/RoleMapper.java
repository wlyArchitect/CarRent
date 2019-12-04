package com.wh.sys.mapper;

import com.wh.sys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询所有的角色
     * 没有参数就全查询
     */
    List<Role> queryAllRole(Role role);

    /**
     * 删除sys_role_menu表中的数据
     *
     * @param roleid
     */
    void deleteRoleMenuByRid(Integer roleid);

    /**
     * 删除sys_role_user表中的数据
     *
     * @param roleid
     */
    void deleteRoleUserByRid(Integer roleid);
    /**
     * TODO 角色操作菜单,
     * 用到了下面两个方法
     *  先是清空sys_role_menu的角色id对应的数据
     *  接着再进行数据的插入操作
     */
    /**
     * 插入新的角色菜单项
     *
     * @param rid
     * @param mid
     */
    void insertRoleMenu(@Param("rid") Integer rid,@Param("mid") Integer mid);

    /**
     * 删除sys_role_user表中的用户id的信息
     * @param userid
     */
    void deleteRoleUserByUid(Integer userid);

    /**
     * 查询用户id对应的角色项
     * @param availableTrue
     * @param userid
     * @return
     */
    List<Role> queryRoleByUid(@Param("available") Integer availableTrue,@Param("uid") Integer userid);
}