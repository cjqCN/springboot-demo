package com.example.demo.web;

import com.example.demo.service.BaseService;
import com.example.demo.util.PagedResult;
import com.example.demo.util.id.ID;
import com.example.demo.util.query.ConditionQueryDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <TService>
 * @param <V>
 * @author chenjinquan
 */
public abstract class BaseController<TService extends BaseService, V> {

    @Autowired
    protected TService service;

    /**
     * 增加T
     *
     * @param v
     * @return
     */
    @ApiOperation(value = "增加", notes = "增加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public boolean insert(@RequestBody V v) throws Exception {
        return service.insert(v);
    }

    /**
     * 批量增加T
     *
     * @param tList
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "批量增加", notes = "批量增加")
    @RequestMapping(value = "/insertByBatch", method = RequestMethod.POST)
    public boolean insertByBatch(@RequestBody List<V> tList) throws Exception {
        return service.insertBatch(tList);
    }

    /**
     * 更新T
     *
     * @param v
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean update(@RequestBody V v) throws Exception {
        return service.updateById(v);
    }

    /**
     * 删除T
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean delete(@RequestParam String id) throws Exception {
        return service.deleteById(ID.newID(id));
    }

    /**
     * 批量删除T
     *
     * @param idList
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/deleteByBatch", method = RequestMethod.POST)
    public boolean deleteByBatch(@RequestBody List<String> idList) throws Exception {
        return service.deleteBatchById(idList.stream().map(x -> ID.newID(x)).collect(Collectors.toList()));
    }

    /**
     * 查看T
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查看", notes = "查看")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public V viewById(@RequestParam String id) throws Exception {
        return (V) service.findById(ID.newID(id));
    }


    /**
     * 根据条件查询
     *
     * @param conditionQueryDTO
     * @return
     */
    @ApiOperation(value = "搜索", notes = "根据条件搜索")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PagedResult<V> selectByCommonRequestVo(@RequestBody ConditionQueryDTO conditionQueryDTO) throws Exception {
        return service.findByConditionQuery(conditionQueryDTO);
    }

}
