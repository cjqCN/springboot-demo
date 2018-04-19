package com.example.demo.bean.converter;


import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * VO、PO 转换器
 *
 * @param <P>
 * @param <V>
 * @author chenjinquan
 */
public interface IConverter<V, P> {

    /**
     * VO -> PO
     *
     * @param v
     * @return
     */
    default P transfer(V v) {
        throw new RuntimeException("该方法需要重写");
    }

    /**
     * PO -> VO
     *
     * @param p
     * @return
     */
    default V retransfer(P p) {
        throw new RuntimeException("该方法需要重写");
    }

    /**
     * 批量VO -> PO
     *
     * @param vList
     * @return
     */
    default List<P> transferByBatch(List<V> vList) {
        if (CollectionUtils.isEmpty(vList)) {
            return null;
        }
        List<P> pList = vList.stream().map(x -> transfer(x)).collect(Collectors.toList());
        return pList;
    }

    /**
     * 批量 PO -> VO
     *
     * @param pList
     * @return
     */
    default List<V> retransferByBatch(List<P> pList) {
        if (CollectionUtils.isEmpty(pList)) {
            return null;
        }
        List<V> vList = pList.stream().map(x -> retransfer(x)).collect(Collectors.toList());
        return vList;
    }

}
