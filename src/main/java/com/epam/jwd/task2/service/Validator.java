package com.epam.jwd.task2.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String INVALID_VALUES = "[a-zA-Zа-яА-Я.,]";
    private static final String VALID_VALUES = "[\\d\\-+/*()]";

    public static boolean isValid(String expression) {
        Pattern invalid = Pattern.compile(INVALID_VALUES);
        Pattern valid = Pattern.compile(VALID_VALUES);
        Matcher invalidMatcher = invalid.matcher(expression);
        Matcher validMatcher = valid.matcher(expression);
        return !invalidMatcher.find() && validMatcher.find();
    }
}