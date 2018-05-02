package com.example.demo.service.impl;

import com.example.demo.bean.converter.IConverter;
import com.example.demo.bean.po.SuperEntity;
import com.example.demo.mapper.BaseMapper;
import com.example.demo.service.BaseService;
import com.example.demo.util.PagedResult;
import com.example.demo.util.id.ID;
import com.example.demo.util.query.ConditionQuery;
import com.example.demo.util.query.ConditionQueryDTO;
import com.example.demo.util.query.ConditionSQLHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


import static com.example.demo.util.TimeUtil.date;
import static com.example.demo.util.token.UserHolder.getCurrentUserId;
import static com.example.demo.util.token.UserHolder.getCurrentUserName;
import static org.springframework.util.ObjectUtils.isEmpty;

@Transactional
public abstract class AbstractService<V, P extends SuperEntity> implements BaseService<V> {

    /**
     * 注入持久化Mapper
     *
     * @return
     */
    abstract BaseMapper<P> mapper();

    /**
     * 注入VO、PO转化器
     *
     * @return
     */
    abstract IConverter<V, P> converter();

    @Override
    public boolean insert(V v) throws Exception {
        throwParamIsEmptyException(v);
        P p = converter().transfer(v);
        p.setCreate(getCurrentUserName(), getCurrentUserId());
        return mapper().insert(p) > 0;
    }

    @Override
    public boolean insertBatch(List<V> vList) throws Exception {
        throwParamIsEmptyException(vList);
        List<P> pList = converter().transferByBatch(vList);
        pList.forEach(p -> p.setCreate(getCurrentUserName(), getCurrentUserId()));
        return mapper().insertBatch(pList) > 0;
    }

    @Override
    public boolean updateById(V v) throws Exception {
        throwParamIsEmptyException(v);
        P p = converter().transfer(v);
        p.setModify(getCurrentUserName(), getCurrentUserId());
        return mapper().updateByPrimaryKeySelective(p) > 0;
    }


    @Override
    public boolean deleteById(ID id) throws Exception {
        throwParamIsEmptyException(id);
        return mapper().pseudoDeleteByPrimaryKey(id.idForLong(), getCurrentUserId(), getCurrentUserName(), date().getTime()) > 0;
    }

    @Override
    public boolean deleteBatchById(List<ID> idList) throws Exception {
        throwParamIsEmptyException(idList);
        return mapper().pseudoDeleteBatchByPrimaryKey(idList.stream().map(x -> x.idForLong()).collect(Collectors.toList()),
                getCurrentUserId(), getCurrentUserName(), date().getTime()) > 0;
    }

    @Transactional(readOnly = true)
    @Override
    public V viewById(ID id) throws Exception {
        throwParamIsEmptyException(id);
        P p = mapper().selectByPrimaryKey(id.idForLong());
        return converter().retransfer(p);
    }

    @Transactional(readOnly = true)
    @Override
    public PagedResult<V> selectByConditionQuery(ConditionQueryDTO conditionQueryDTO) throws Exception {
        //cjqtodo 代码示范，应子类实现，推荐使用AliasMapperConditionSQLHelper
        PagedResult<P> pagedResult = ConditionQuery.SIMPLE_CONDITION_QUERY.conditionQuery(mapper(),
                conditionQueryDTO, new ConditionSQLHelper());
        return new PagedResult<V>(converter().retransferByBatch(pagedResult.getItems()),
                pagedResult.getTotalCount());
    }

    final static Exception PARAM_IS_EMPTY_EXCEPTION = new Exception("参数为空");

    private void throwParamIsEmptyException(Object obj) throws Exception {
        if (isEmpty(obj)) {
            throw PARAM_IS_EMPTY_EXCEPTION;
        }
    }

    final static Exception ID_IS_EMPTY_EXCEPTION = new Exception("Id为空");

    private void throwIdIsNullException(P p) throws Exception {
        if (isEmpty(p.getId())) {
            throw ID_IS_EMPTY_EXCEPTION;
        }
    }

}
