package com.example.demo.service.impl;


import com.example.demo.bean.converter.IConverter;
import com.example.demo.bean.po.SuperEntity;
import com.example.demo.mapper.SuperMapper;
import com.example.demo.service.BaseService;
import com.example.demo.util.PagedResult;
import com.example.demo.util.id.ID;
import com.example.demo.util.query.ConditionQuery;
import com.example.demo.util.query.ConditionQueryDTO;
import com.example.demo.util.query.ConditionSQL;
import com.example.demo.util.query.ConditionSQLHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


import static com.example.demo.util.TimeUtil.dateForLong;
import static com.example.demo.util.token.UserHolder.getCurrentUserId;
import static com.example.demo.util.token.UserHolder.getCurrentUserName;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Transactional
public abstract class AbstractService<V, P extends SuperEntity> implements BaseService<V> {

    /**
     * 注入持久化Mapper
     *
     * @return
     */
    protected abstract SuperMapper<P> mapper();

    /**
     * 注入VO、PO转化器
     *
     * @return
     */
    protected abstract IConverter<V, P> converter();

    /**
     * 使用 mybatis plus
     *
     * @param v
     * @return
     */
    @Override
    public boolean insert(V v) throws Exception {
        P p = converter().transfer(checkNotNull(v));
        p.setCreate(getCurrentUserName(), getCurrentUserId());
        return mapper().insert(p) > 0;
    }

    @Override
    public boolean insertBatch(List<V> vList) throws Exception {
        if (isEmpty(vList)) {
            throw new Exception("传入数据为空");
        }
        List<P> pList = converter().transferByBatch(vList);
        pList.forEach(p -> p.setCreate(getCurrentUserName(), getCurrentUserId()));
        return mapper().insertBatch(pList) > 0;
    }

    /**
     * 使用 mybatis plus
     *
     * @param v
     * @return
     */
    @Override
    public boolean updateById(V v) throws Exception {
        P p = converter().transfer(checkNotNull(v));
        p.setModify(getCurrentUserName(), getCurrentUserId());
        return mapper().updateById(p) > 0;
    }


    @Override
    public boolean deleteById(ID id) throws Exception {
        return mapper().softDeleteBatchById(checkNotNull(id).idForLong(), getCurrentUserId(), getCurrentUserName(), dateForLong()) > 0;
    }

    @Override
    public boolean deleteBatchById(List<ID> idList) throws Exception {
        if (isEmpty(idList)) {
            throw new Exception("传入数据为空");
        }
        return mapper().softDeleteBatchByIdList(idList.stream().map(x -> x.idForLong()).collect(Collectors.toList()),
                getCurrentUserId(), getCurrentUserName(), dateForLong()) > 0;
    }

    @Transactional(readOnly = true)
    @Override
    public V findById(ID id) throws Exception {
        P p = mapper().selectById(checkNotNull(id).idForLong());
        return converter().retransfer(p);
    }

    @Transactional(readOnly = true)
    @Override
    public PagedResult<V> findByConditionQuery(ConditionQueryDTO conditionQueryDTO) throws Exception {
        //cjqtodo 代码示范，应子类实现，推荐使用AliasMapperConditionSQLHelper
        PagedResult<P> pagedResult = ConditionQuery.SIMPLE_CONDITION_QUERY.conditionQuery(mapper(),
                conditionQueryDTO, ConditionSQLHelper.instance);
        return new PagedResult<V>(converter().retransferByBatch(pagedResult.getItems()),
                pagedResult.getTotalCount(), pagedResult.getPageIndex());
    }
    @Transactional(readOnly = true)
    @Override
    public List<V> findByConditions(ConditionSQL conditionSQL){
        return converter().retransferByBatch(mapper().findByConditions(conditionSQL));
    }

}
