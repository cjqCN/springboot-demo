package com.example.demo.service.impl;

import com.example.demo.bean.po.User;
import com.example.demo.mapper.UserDao;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public int addUser(User user) throws Exception {
        return userDao.insert(user);
    }

    @Override
    public int updateUser(User user) throws Exception {
        return userDao.updateByPrimaryKey(user);
    }

    @Override
    public User findUserByUserId(Long userId) throws Exception {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public int deleteUserByUserId(Long userId) throws Exception {
        return userDao.deleteByPrimaryKey(userId);
    }
}
