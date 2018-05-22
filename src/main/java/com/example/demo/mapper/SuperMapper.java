package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.util.query.ConditionSQL;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SuperMapper<T> extends BaseMapper<T> {

    /**
     * 软删除
     *
     * @param id           主键
     * @param deleteUserId 删除人id
     * @param deleteDate   删除时间
     * @return
     */
    int softDeleteBatchById(@Param("id") Long id, @Param("deleteUserId") Long deleteUserId,
                            @Param("deleteUserName") String deleteUserName, @Param("deleteDate") Long deleteDate);

    /**
     * 软删除
     *
     * @param ids          主键
     * @param deleteUserId 删除人id
     * @param deleteDate   删除时间
     * @return
     */
    int softDeleteBatchByIdList(@Param("ids") List<Long> ids, @Param("deleteUserId") Long deleteUserId,
                                @Param("deleteUserName") String deleteUserName, @Param("deleteDate") Long deleteDate);

    /**
     * 批量新增
     *
     * @param records
     * @return
     */
    int insertBatch(List<T> records);


    /**
     * 根据查询条件查数据
     *
     * @param conditionSQL
     * @return
     */
    List<T> findByConditions(@Param("conditionSQL") ConditionSQL conditionSQL);
}
