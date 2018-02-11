package com.ssm.cluster.dao;

import com.ssm.cluster.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author 13
 * @date 2018-02-11 13:39:08
 */
public interface UserDao {
    /**
     * 登录
     *
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 查找用户列表
     *
     * @param map
     * @return
     */
    List<User> findUsers(Map<String, Object> map);

    /**
     * @param map
     * @return
     */
    int getTotalUser(Map<String, Object> map);

    /**
     * 实体修改
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteUser(Integer id);


    /**
     * 批量删除
     *
     * @param id
     * @return
     */
    int deleteBatch(Object[] id);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);
}
