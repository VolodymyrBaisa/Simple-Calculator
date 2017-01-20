package com.example.bios.mvvm.model;

import java.util.LinkedList;

/**
 * Created by BIOS on 12/17/2016.
 */

public class MathOperations {
    private final LinkedList<String> list = new LinkedList<>();

    public MathOperations() {
    }

    public MathOperations(LinkedList<String> exp) {
        addNewList(exp);
    }

    public void addExpression(LinkedList<String> exp) {
        addNewList(exp);
    }

    private void addNewList(LinkedList<String> exp) {
        if (list.isEmpty()) {
            list.addAll(exp);
        } else {
            list.clear();
            list.addAll(exp);
        }
    }

    public Double result() throws ArithmeticException, NumberFormatException {
        final StringBuilder result = new StringBuilder();
        if (!list.isEmpty()) {
            LinkedList<String> values = calculateValue();
            for (String value : values) {
                result.append(value);
            }
        }
        return Double.parseDouble(result.toString());
    }

    private LinkedList<String> calculateValue() throws ArithmeticException, NumberFormatException {
        if (list.contains(Operators.DIVIDE.getOperator())) {
            divide();
        }

        if (list.contains(Operators.MULTIPLY.getOperator())) {
            multiply();
        }

        if (list.contains(Operators.SUBTRACT.getOperator())) {
            subtract();
        }

        if (list.contains(Operators.PLUS.getOperator())) {
            plus();
        }

        return list;
    }

    private void divide() throws ArithmeticException, NumberFormatException {
        int operator = list.indexOf(Operators.DIVIDE.getOperator());
        Double[] doubleValues = getValues(operator);

        if (doubleValues.length != 0) {
            double firstValue = doubleValues[0];
            double secondValue = doubleValues[1];
            double result = firstValue / secondValue;
            commitResult(result, operator);
            calculateValue();
        }
    }

    private void multiply() throws ArithmeticException, NumberFormatException {
        int operator = list.indexOf(Operators.MULTIPLY.getOperator());
        Double[] doubleValues = getValues(operator);

        if (doubleValues.length != 0) {
            double firstValue = doubleValues[0];
            double secondValue = doubleValues[1];
            double result = firstValue * secondValue;
            commitResult(result, operator);
            calculateValue();
        }
    }

    private void subtract() throws ArithmeticException, NumberFormatException {
        int operator = list.indexOf(Operators.SUBTRACT.getOperator());
        Double[] doubleValues = getValues(operator);

        if (doubleValues.length != 0)
        {
            double firstValue = doubleValues[0];
            double secondValue = doubleValues[1];
            double result = firstValue - secondValue;
            commitResult(result, operator);
            calculateValue();
        }

    }

    private void plus() throws ArithmeticException, NumberFormatException {
        int operator = list.indexOf(Operators.PLUS.getOperator());
        Double[] doubleValues = getValues(operator);

        if (doubleValues.length != 0) {
            double firstValue = doubleValues[0];
            double secondValue = doubleValues[1];
            double result = firstValue + secondValue;
            commitResult(result, operator);
            calculateValue();
        }
    }

    private Double[] getValues(int operator) throws ArithmeticException, NumberFormatException {
        if (operator > 0 && operator < list.size() - 1) {
            double firstValue = Double.parseDouble(list.get(operator - 1));
            double secondValue = Double.parseDouble(list.get(operator + 1));
            return new Double[]{firstValue, secondValue};
        }

        return new Double[]{};
    }

    private void commitResult(double result, int operator) {
        list.set(operator - 1, String.valueOf(result));
        int values[] = {operator + 1, operator};
        for (int value : values) {
            list.remove(value);
        }
    }
}
