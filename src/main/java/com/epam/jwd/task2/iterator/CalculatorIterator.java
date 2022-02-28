package com.epam.jwd.task2.iterator;

import com.epam.jwd.task2.service.Validator;

import java.util.Iterator;

public class CalculatorIterator implements Iterator<String> {
    private final String expression;
    private int counter;


    public CalculatorIterator(String expression) {
        if (!Validator.isValid(expression)) {
            throw new RuntimeException();
        }
        this.expression = expression.replace(" ", "");
    }

    @Override
    public boolean hasNext() {
        return counter < expression.length();
    }

    @Override
    public String next() {
        StringBuilder operand = new StringBuilder();
        char element = expression.charAt(counter);
        while (Character.isDigit(element)) {
            operand.append(element);
            counter++;
            if (counter == expression.length()) {
                return operand.toString();
            }
            element = expression.charAt(counter);
        }

        if (operand.length() != 0) {
            return operand.toString();
        }
        counter++;
        return Character.toString(element);
    }

}
