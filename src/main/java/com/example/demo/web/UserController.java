package com.example.demo.web;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户", notes = "根据userId去获取用户", httpMethod = "GET")
    @RequestMapping(value = "/user_find", method = RequestMethod.GET)
    public User getUser(@RequestParam("user_id") Long userId) throws Exception {
        log.info("<---{}", userId);
        return userService.findUserByUserId(userId);
    }

    @ApiOperation(value = "删除用户", notes = "根据userId去删除用户")
    @RequestMapping(value = "/user_delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("user_id") Long userId) throws Exception {
        log.info("<---{}", userId);
        int i = userService.deleteUserByUserId(userId);
        return i > 0 ? SUCCESS : FAILED;
    }

    @ApiOperation(value = "添加用户", notes = "根据用户信息去添加用户")
    @RequestMapping(value = "/user_add", method = RequestMethod.POST)
    public String getUser(@RequestBody User user) throws Exception {
        log.info("<---{}", user);
        int i = userService.addUser(user);
        return i > 0 ? SUCCESS : FAILED;
    }

    @ApiOperation(value = "更新用户", notes = "根据用户信息去更新用户")
    @RequestMapping(value = "/user_update", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user) throws Exception {
        log.info("<---{}", user);
        int i = userService.updateUser(user);
        return i > 0 ? SUCCESS : FAILED;
    }

}
