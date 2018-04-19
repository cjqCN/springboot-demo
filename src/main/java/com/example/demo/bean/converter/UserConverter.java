package com.example.demo.bean.converter;

import com.example.demo.bean.dto.UserDTO;
import com.example.demo.bean.po.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class UserConverter implements IConverter<UserDTO, User> {

    @Override
    public UserDTO retransfer(User user) {
        if (isEmpty(user)) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public User transfer(UserDTO userDTO) {
        if (isEmpty(userDTO)) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}
