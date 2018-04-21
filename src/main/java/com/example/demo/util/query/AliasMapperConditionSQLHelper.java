package com.example.demo.util.query;


import java.util.List;
import java.util.Map;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * 使用一个Map <p></>
 * 适配前端传过来的字段与数据库字段差异，同时做白名单控制，不在名单内抛出异常
 *
 * @author chenjinquan
 */
public class AliasMapperConditionSQLHelper extends ConditionSQLHelper {

    Map<String, String> conditionNameMap;

    public AliasMapperConditionSQLHelper(Map<String, String> conditionNameMap) {
        this.conditionNameMap = conditionNameMap;
    }


    @Override
    ConditionSQL create(ConditionQueryDTO conditionQueryDTO) throws Exception {
        replaceAlias(this.conditionNameMap, conditionQueryDTO);
        return super.create(conditionQueryDTO);
    }

    void replaceAlias(Map<String, String> conditionNameMap, ConditionQueryDTO conditionQueryDTO) throws Exception {
        if (isEmpty(conditionNameMap)) {
            throw new Exception("ConditionNameMap is empty");
        }
        String alias = null;
        String dbField = null;
        List<Condition> conditions = conditionQueryDTO.getConditions();
        if (!isEmpty(conditions)) {
            for (Condition condition : conditions) {
                alias = condition.getConditionName();
                dbField = conditionNameMap.get(alias);
                if (isEmpty(dbField)) {
                    throw new Exception("条件名有误");
                }
                condition.setConditionName(dbField);
            }
        }
        List<Sort> sorts = conditionQueryDTO.getSorts();
        if (!isEmpty(sorts)) {
            for (Sort sort : sorts) {
                alias = sort.getSortField();
                dbField = conditionNameMap.get(alias);
                if (isEmpty(dbField)) {
                    throw new Exception("条件名有误");
                }
                sort.setSortField(dbField);
            }
        }
    }


}
