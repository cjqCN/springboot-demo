package com.example.demo.web;

import com.example.demo.bean.dto.UserDTO;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "UserController API", description = "用户管理 API")
@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController extends BaseController<UserService, UserDTO> {


    @ApiOperation(value = "查看用户", notes = "根据用户名查看用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String", defaultValue = "userName", paramType = "query", required = true)
    })
    @RequestMapping(value = "/viewByAcount", method = RequestMethod.GET)
    public UserDTO viewByAcount(@RequestParam(required = true) String userName) throws Exception {
        return null;
    }

}
