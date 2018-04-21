package com.example.demo.util.query;


import com.example.demo.mapper.ConditionQueryMapper;
import com.example.demo.util.PagedResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * 条件查询
 *
 * @author chenjinquan
 */
public interface ConditionQuery {
    <T> PagedResult<T> conditionQuery(ConditionQueryMapper<T> conditionQueryMapper,
                                      ConditionQueryDTO conditionQueryDTO, ConditionSQLHelper conditionSQLHelper) throws Exception;

    ConditionQuery SIMPLE_CONDITION_QUERY = new ConditionQuery() {
        final static int PAGE_INDEX = 1;
        final static int PAGE_SIZE = 10;

        @Override
        public <T> PagedResult<T> conditionQuery(ConditionQueryMapper<T> conditionQueryMapper,
                                                 ConditionQueryDTO conditionQueryDTO, ConditionSQLHelper conditionSQLHelper) throws Exception {
            if (conditionQueryDTO == null) {
                throw new Exception("条件为空");
            }
            if (conditionQueryDTO.getPageIndex() == null || conditionQueryDTO.getPageIndex() < 1) {
                conditionQueryDTO.setPageIndex(PAGE_INDEX);
            }
            if (conditionQueryDTO.getPageSize() == null || conditionQueryDTO.getPageSize() < 1) {
                conditionQueryDTO.setPageSize(PAGE_SIZE);
            }
            ConditionSQL conditionSQL = conditionSQLHelper.create(conditionQueryDTO);
            if (conditionSQL == null) {
                throw new Exception("条件有误");
            }
            PageHelper.startPage(conditionQueryDTO.getPageIndex(), conditionQueryDTO.getPageSize());
            List<T> tList = conditionQueryMapper.findByConditions(conditionSQL);
            long total = new PageInfo<>(tList).getTotal();
            return new PagedResult<T>(tList, total);
        }
    };
}
