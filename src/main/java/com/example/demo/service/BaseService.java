package com.example.demo.service;



import com.example.demo.util.PagedResult;
import com.example.demo.util.id.ID;
import com.example.demo.util.query.ConditionQueryDTO;

import java.util.List;

/**
 * @param <V>
 * @author chenjinquan
 */
public interface BaseService<V> {

    boolean insert(V v) throws Exception;

    boolean insertBatch(List<V> vList) throws Exception;

    boolean updateById(V v) throws Exception;

    boolean updatePatchById(List<V> vList) throws Exception;

    boolean deleteById(ID id) throws Exception;

    boolean deleteBatchById(List<ID> idList) throws Exception;

    V viewById(ID id) throws Exception;

    PagedResult<V> selectByConditionQuery(ConditionQueryDTO conditionQueryDTO) throws Exception;
}
