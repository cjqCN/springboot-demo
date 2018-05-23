package com.example.demo.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.bean.converter.IConverter;
import com.example.demo.bean.converter.UserConverter;
import com.example.demo.bean.dto.UserDTO;
import com.example.demo.bean.po.User;
import com.example.demo.mapper.SuperMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.PagedResult;
import com.example.demo.util.query.AliasMapperConditionSQLHelper;
import com.example.demo.util.query.ConditionQuery;
import com.example.demo.util.query.ConditionQueryDTO;
import com.example.demo.util.query.ConditionSQLHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends AbstractService<UserDTO, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserConverter userConverter;

    @Override
    protected SuperMapper<User> mapper() {
        return userMapper;
    }

    @Override
    protected IConverter<UserDTO, User> converter() {
        return userConverter;
    }


    @Transactional(readOnly = true)
    @Override
    public PagedResult<UserDTO> findByConditionQuery(ConditionQueryDTO conditionQueryDTO) throws Exception {
        Map<String, String> conditionNameMap = new HashMap<>();
        conditionNameMap.put("u", "id");
        PagedResult<User> pagedResult = ConditionQuery.SIMPLE_CONDITION_QUERY.conditionQuery(mapper(), conditionQueryDTO, new AliasMapperConditionSQLHelper(conditionNameMap));
        return new PagedResult<UserDTO>(converter().retransferByBatch(pagedResult.getItems()), pagedResult.getTotalCount());
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
