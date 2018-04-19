package com.example.demo.service.impl;

import com.example.demo.bean.converter.IConverter;
import com.example.demo.bean.converter.UserConverter;
import com.example.demo.bean.dto.UserDTO;
import com.example.demo.bean.po.User;
import com.example.demo.mapper.BaseMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<UserDTO, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserConverter userConverter;

    @Override
    protected BaseMapper<User> mapper() {
        return userMapper;
    }

    @Override
    protected IConverter<UserDTO, User> converter() {
        return userConverter;
    }

    /**
     * 重置密码
     *
     * @return
     * @throws Exception
     */
    @Override
    public boolean resetPassWord() throws Exception {
        return false;
    }

    /**
     * 修改密码
     *
     * @return
     * @throws Exception
     */
    @Override
    public boolean changePassWord() throws Exception {
        return false;
    }
}
