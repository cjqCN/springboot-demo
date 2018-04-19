package com.example.demo.dao;

import com.example.demo.util.commonquery.ConditionsPo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BaseMapper<T> {

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);


    /**
     * 软删除
     *
     * @param id           主键
     * @param deleteUserId 删除人id
     * @param deleteDate   删除时间
     * @return
     */
    int pseudoDeleteByPrimaryKey(@Param("id") Long id, @Param("deleteUserId") Long deleteUserId,
                                 @Param("deleteDate") Date deleteDate);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 根据ID搜索
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 查找全部
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据ID更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);


    int updateByPrimaryKeySelective(T record);


    int insertSelective(T record);

    /**
     * 根据查询条件查数量
     *
     * @param conditionsPo
     * @return
     */
    long countByConditions(@Param("conditionsPo") ConditionsPo conditionsPo);

    /**
     * 根据查询条件查数据
     *
     * @param conditionsPo
     * @return
     */
    List<T> findByConditions(@Param("conditionsPo") ConditionsPo conditionsPo);


}
