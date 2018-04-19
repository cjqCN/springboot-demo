package com.example.demo.util;


import lombok.Data;

import java.util.List;

/**
 * 结果集
 * @param <T>
 */
@Data
public class PagedResult<T> {

    /**
     * 结果集
     */
    List<T> items;
    /**
     * 记录数
     */
    long totalCount;

    public PagedResult() {}

    public PagedResult(List<T> items) {
        this();
        this.items = items;
    }

    public PagedResult(List<T> items, long totalCount) {
        this(items);
        this.totalCount = totalCount;
    }

}
