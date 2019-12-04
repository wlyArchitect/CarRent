package com.wh.sys.mapper;

import com.wh.sys.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    /**
     * 查询所有菜单(角色权限为超级管理员)
     * 没有参数就全查询
     */
    List<Menu> queryAllMenu(Menu menu);

    /**
     * 根据pid(子级菜单项)查询菜单数量
     * @param pid
     * @return
     */
    Integer queryMenuByPid(@Param("pid")Integer pid);

    /**
     * 根据菜单id删除sys_role_menu里面的数据
     * @param mid
     */
    void deleteRoleMenuByMid(@Param("mid")Integer mid);

    /**
     * 根据角色的id 查询出 拥有的菜单项
     * sys_role角色表查询出 sys_menu菜单表中的数据,借助中间表sys_role_menu
     * @param availableTrue
     * @param roleid
     * @return
     */
    List<Menu> queryMenuByRoleId(@Param("available") Integer availableTrue, @Param("rid") Integer roleid);

    /**
     * 根据用户id查询菜单
     * @param available
     * @param userId
     * @return
     */
    List<Menu> queryMenuByUid(@Param("available") Integer available, @Param("uid") Integer userId);
}