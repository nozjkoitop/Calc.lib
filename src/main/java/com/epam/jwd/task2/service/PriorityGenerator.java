package com.epam.jwd.task2.service;

import java.util.HashMap;
import java.util.Map;

public class PriorityGenerator {
    private static final Map<String, Integer> operationPriorityMap = new HashMap<>();

    static {
        addOperator("*", 1);
        addOperator("/", 1);
        addOperator("+", 2);
        addOperator("-", 2);
    }

    public static void addOperator(String operator, Integer priority) {
        operationPriorityMap.put(operator, priority);
    }

    public static Integer getPriority(String operator) {
        return operationPriorityMap.get(operator);
    }
}
