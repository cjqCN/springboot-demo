package com.example.demo.service.impl;

import com.example.demo.bean.converter.IConverter;
import com.example.demo.bean.po.SuperEntity;
import com.example.demo.bean.po.User;
import com.example.demo.mapper.BaseMapper;
import com.example.demo.service.BaseService;
import com.example.demo.util.commonquery.CommonRequestVo;
import com.example.demo.util.commonquery.ConditionUtil;
import com.example.demo.util.commonquery.ConditionsPo;
import com.example.demo.util.PagedResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.demo.util.id.ID;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static com.example.demo.util.TimeUtil.date;
import static com.example.demo.util.token.UserHolder.getCurrentUserId;
import static com.example.demo.util.token.UserHolder.getCurrentUserName;
import static org.springframework.util.ObjectUtils.isEmpty;

//TODO 需要修改细节，暂时是粗略地实现
@Transactional(rollbackFor = Exception.class)
public abstract class AbstractService<V, P extends SuperEntity> implements BaseService<V> {

    /**
     * 注入持久化Mapper
     *
     * @return
     */
    protected abstract BaseMapper<P> mapper();

    /**
     * 注入VO、PO转化器
     *
     * @return
     */
    protected abstract IConverter<V, P> converter();

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
        //TODO 改批量插入
        for (P p : pList) {
            p.setCreate(getCurrentUserName(), getCurrentUserId());
            mapper().insert(p);
        }
        return true;
    }

    @Override
    public boolean updateById(V v) throws Exception {
        throwParamIsEmptyException(v);
        P p = converter().transfer(v);
        p.setModify(getCurrentUserName(), getCurrentUserId());
        return mapper().updateByPrimaryKeySelective(p) > 0;
    }

    @Override
    public boolean updatePatchById(List<V> vList) throws Exception {
        throwParamIsEmptyException(vList);
        List<P> pList = converter().transferByBatch(vList);
        //TODO 改批量插入
        for (P p : pList) {
            p.setModify(getCurrentUserName(), getCurrentUserId());
            mapper().updateByPrimaryKeySelective(p);
        }
        return true;
    }

    @Override
    public boolean deleteById(ID id) throws Exception {
        throwParamIsEmptyException(id);
        return mapper().pseudoDeleteByPrimaryKey(id.idForLong(), getCurrentUserId(), date()) > 0;
    }

    @Override
    public boolean deleteBatchById(List<ID> idList) throws Exception {
        throwParamIsEmptyException(idList);
        for (ID id : idList) {
            mapper().pseudoDeleteByPrimaryKey(id.idForLong(), getCurrentUserId(), date());
        }
        return true;
    }

    @Override
    public V viewById(ID id) throws Exception {
        throwParamIsEmptyException(id);
        P p = mapper().selectByPrimaryKey(id.idForLong());
        return converter().retransfer(p);
    }

    @Override
    public PagedResult<V> selectByCommonRequestVo(CommonRequestVo commonRequestVo) throws Exception {
        //TODO
        throwParamIsEmptyException(commonRequestVo);
        ConditionsPo conditionsPo = ConditionUtil.commonRequestVo2ConditionsPo(commonRequestVo, User.class);
        PageHelper.startPage(2, 1);
        List<P> pList = mapper().findByConditions(conditionsPo);
        //取记录总条数
        long total = new PageInfo<>(pList).getTotal();

        if (!isEmpty(pList)) {
            return new PagedResult<V>(converter().retransferByBatch(pList), total);
        }
        return null;

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
