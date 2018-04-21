package com.example.demo.mapper;

import com.example.demo.util.query.ConditionSQL;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ConditionQueryMapper<T> {

    /**
     * 根据查询条件查数量
     *
     * @param conditionSQL
     * @return
     */
    long countByConditions(@Param("conditionSQL") ConditionSQL conditionSQL);

    /**
     * 根据查询条件查数据
     *
     * @param conditionSQL
     * @return
     */
    List<T> findByConditions(@Param("conditionSQL") ConditionSQL conditionSQL);
}
