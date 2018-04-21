package com.example.demo.util.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ConditionQueryDTO {

    @ApiModelProperty(value = "查询第几页")
    private Integer pageIndex;

    @ApiModelProperty(value = "页码")
    private Integer pageSize;

    @ApiModelProperty(value = "查询条件")
    private List<Condition> conditions;

    @ApiModelProperty(value = "排序字段组")
    private List<Sort> sorts;
}
