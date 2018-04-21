package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BaseMapper<T> extends ConditionQueryMapper<T> {

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




}
