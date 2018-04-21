package com.example.demo.mapper;


import com.example.demo.bean.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}