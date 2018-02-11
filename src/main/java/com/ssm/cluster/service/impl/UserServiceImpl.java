package com.ssm.cluster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ssm.cluster.dao.UserDao;
import com.ssm.cluster.entity.User;
import com.ssm.cluster.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<User> queryList(Map<String, Object> map) {
        return userDao.findUsers(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userDao.getTotalUser(map);
    }

    @Override
    public void save(User user) {
        userDao.addUser(user);
    }

    @Override
    public void update(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        userDao.deleteBatch(ids);
    }

    @Override
    public User queryObject(Integer id) {
        User user = userDao.getUserById(id);
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

}
