package com.example.demo.util.query;


public enum ComparisonSymbol {
    EQ(0, "=", "等于"),
    LIKE(1, "LIKE", "模糊"),
    IN(2, "IN", "在某范围"),
    GREAT(3, ">", "大于"),
    LESS(4, "<", "小于"),
    GREATEQ(5, ">=", "大于等于"),
    LESSEQ(6, "<=", "小于等于"),
    NQ(7, "<>", "不等于");

    private int value;
    private String sqlSymbol;
    private String info;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSqlSymbol(String sqlSymbol) {
        this.sqlSymbol = sqlSymbol;
    }

    public String getSqlSymbol() {
        return sqlSymbol;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    ComparisonSymbol(final int value, final String sqlSymbol, final String info) {
        this.value = value;
        this.sqlSymbol = sqlSymbol;
        this.info = info;
    }


}