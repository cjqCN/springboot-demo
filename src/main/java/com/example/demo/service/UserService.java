package com.example.demo.service;

import com.example.demo.bean.dto.UserDTO;


public interface UserService extends BaseService<UserDTO> {

    /**
     * 重置密码
     *
     * @return
     * @throws Exception
     */
    boolean resetPassWord() throws Exception;

    /**
     * 修改密码
     *
     * @return
     * @throws Exception
     */
    boolean changePassWord() throws Exception;
}
