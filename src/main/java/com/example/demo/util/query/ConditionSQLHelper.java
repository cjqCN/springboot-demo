package com.example.demo.util.query;



import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.util.StringUtils.isEmpty;

public class ConditionSQLHelper {

    final static String INVALID_ARGUMENT = "非法参数";
    final static String BLANK = " ";
    final static String ORDER_BY = BLANK + "ORDER BY" + BLANK;
    final static String AND = BLANK + "AND" + BLANK;
    final static String COMMA = " ,";
    final static String SINGLE_QUOTE = "'";

    ConditionSQL create(ConditionQueryDTO conditionQueryDTO) throws Exception {
        if (conditionQueryDTO == null) {
            return null;
        }
        ConditionSQL conditionSQL = new ConditionSQL();
        if (conditionQueryDTO.getConditions() != null) {
            conditionScript(conditionQueryDTO.getConditions(), conditionSQL);
        }
        if (conditionQueryDTO.getSorts() != null) {
            sortScript(conditionQueryDTO.getSorts(), conditionSQL);
        }
        if (!isValid(conditionSQL.getConditions()) || !isValid(conditionSQL.getSort())) {
            throw new Exception(INVALID_ARGUMENT);
        }
        return conditionSQL;
    }

    void conditionScript(List<Condition> conditions, ConditionSQL conditionSQL) {
        StringBuffer conditionSql = new StringBuffer();
        for (Condition condition : conditions) {
            String conditionName = condition.getConditionName().trim();
            String comparisonSymbol = condition.getComparisonSymbol().getSqlSymbol();
            String conditionValue = condition.getConditionValue().trim();
            conditionSql.append(conditionName)
                    .append(BLANK)
                    .append(comparisonSymbol)
                    .append(BLANK)
                    .append(SINGLE_QUOTE)
                    .append(conditionValue)
                    .append(SINGLE_QUOTE);
            conditionSql.append(AND);
        }
        if (!isEmpty(conditionSql.toString())) {
            conditionSQL.setConditions(conditionSql.substring(0, conditionSql.length() - AND.length()));
        }
    }

    final static Set<String> SORT_SYM = Stream.of("asc", "desc", "ASC", "DESC").collect(Collectors.toSet());

    void sortScript(List<Sort> sorts, ConditionSQL conditionSQL) throws Exception {
        StringBuffer sortSql = new StringBuffer(ORDER_BY);
        String sortField = null;
        String sortType = null;
        for (Sort sort : sorts) {
            sortField = sort.getSortField();
            sortType = sort.getSortSymbol().getSqlSymbol();
            sortSql.append(sortField).append(BLANK).append(sortType).append(COMMA);
        }
        if (!isEmpty(sortSql.toString())) {
            conditionSQL.setSort(sortSql.substring(0, sortSql.length() - COMMA.length()));
        }
    }

    /**
     * 防注入<br/>
     * 正则表达式
     **/
    protected static String reg = "(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
    protected static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

    protected static boolean isValid(String str) {
        if (isEmpty(str)) {
            return true;
        }
        String[] tokens = str.split(" ");
        for (String token : tokens) {
            if (sqlPattern.matcher(token).find()) {
                return false;
            }
        }
        return true;
    }
}
