package com.example.demo.util.query;


public enum SortSymbol {

    ASC(0, "ASC", "升序排列"),
    asc(1, "asc", "升序排列"),
    DESC(2, "DESC", "降序排列"),
    desc(3, "desc", "降序排列");

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


    SortSymbol(final int value, final String sqlSymbol, final String info) {
        this.value = value;
        this.sqlSymbol = sqlSymbol;
        this.info = info;
    }
}
