package com.wh.sys.mapper;

import com.wh.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
     * 登陆
     */
    User login(User user);
    /**
     * 查询用户+模糊查询
     */
    List<User> queryAllUser(User user);

    /**
     * 保存用户分配的角色
     * @param userid
     * @param roleid
     */
    void insertUserRole(@Param("uid") Integer userid, @Param("rid") Integer roleid);
}