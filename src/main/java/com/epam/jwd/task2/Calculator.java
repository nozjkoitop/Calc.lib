package com.epam.jwd.task2;

import com.epam.jwd.task2.iterator.CalculatorIterator;
import com.epam.jwd.task2.service.Validator;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.epam.jwd.task2.service.PriorityGenerator.getPriority;

public class Calculator {
    private final CalculatorIterator iterator;
    private final Deque<Double> operands = new ArrayDeque<>();
    private final Deque<String> operators = new ArrayDeque<>();

    public Calculator(CalculatorIterator iterator) {
        this.iterator = iterator;
    }

    public Calculator(String expression) {
        if (!Validator.isValid(expression)) {
            throw new RuntimeException();
        }
        this.iterator = new CalculatorIterator(expression);

    }

    public Double solve() {
        while (iterator.hasNext()) {
            String element = iterator.next();
            selectOperation(element);
        }
        while (!operators.isEmpty()) {
            calculate();
        }
        return operands.pop();
    }

    private void selectOperation(String element) {
        try {
            addOperand(element);
        } catch (NumberFormatException e) {
            countTempValue(element);
        }
    }

    private void addOperand(String element) {
        double number = Double.parseDouble(element);
        operands.push(number);
    }

    private void countTempValue(String element) {
        if (isOpeningBracket(element)) {
            operators.push(element);
        } else if (isClosingBracket(element)) {
            countBrackets();
            operators.pop();
        } else {
            if (operators.isEmpty()) {
                operators.push(element);
            } else {
                countByPriority(element);
                operators.push(element);
            }
        }
    }

    private void countBrackets() {
        while (!isOpeningBracket(operators.peek())) {
            calculate();
        }
    }

    private void countByPriority(String element) {
        Integer priority = getPriority(element);
        while (!operators.isEmpty() && !isOpeningBracket(operators.peek()) && !isClosingBracket(operators.peek()) &&
                priority >= getPriority(operators.peek())) {
            calculate();
        }
    }

    private void calculate() {
        String operator = operators.pop();
        Double n2 = operands.pop();
        Double n1 = operands.pop();
        Double result = switch (operator) {
            case "+" -> n1 + n2;
            case "-" -> n1 - n2;
            case "*" -> n1 * n2;
            case "/" -> n1 / n2;
            default -> throw new RuntimeException();
        };
        operands.push(result);
    }

    private boolean isClosingBracket(String element) {
        return element.equals(")");
    }

    private boolean isOpeningBracket(String element) {
        return element.equals("(");
    }

}


