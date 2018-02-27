package com.example.demo.service;

import com.example.demo.bean.User;

public interface UserService {

    /**
     * @param user
     * @throws Exception
     */
    int addUser(User user) throws Exception;

    /**
     * @param user
     * @return
     * @throws Exception
     */
    int updateUser(User user) throws Exception;

    /**
     * @param userId
     * @return
     * @throws Exception
     */
    User findUserByUserId(Long userId) throws Exception;

    /**
     * @param userId
     * @throws Exception
     */
    int deleteUserByUserId(Long userId) throws Exception;

}
