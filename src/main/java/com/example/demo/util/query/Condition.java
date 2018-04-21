package com.example.demo.util.query;

import lombok.Data;

@Data
public class Condition {
    String conditionName;
    ComparisonSymbol comparisonSymbol;
    String conditionValue;
}

