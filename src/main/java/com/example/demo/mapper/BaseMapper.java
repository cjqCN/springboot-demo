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
    @Deprecated
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
                                 @Param("deleteUserName") String deleteUserName, @Param("deleteDate") Long deleteDate);


    /**
     * 软删除
     *
     * @param ids          主键
     * @param deleteUserId 删除人id
     * @param deleteDate   删除时间
     * @return
     */
    int pseudoDeleteBatchByPrimaryKey(@Param("ids") List<Long> ids, @Param("deleteUserId") Long deleteUserId,
                                      @Param("deleteUserName") String deleteUserName, @Param("deleteDate") Long deleteDate);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 批量新增
     *
     * @param records
     * @return
     */
    int insertBatch(List<T> records);

    /**
     * 新增
     * @param record
     * @return
     */
    int insertSelective(T record);


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
    @Deprecated
    int updateByPrimaryKey(T record);

    /**
     * 根据ID更新，忽略空字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

}
