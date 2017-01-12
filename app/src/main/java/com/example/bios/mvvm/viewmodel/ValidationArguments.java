package com.example.bios.mvvm.viewmodel;

import com.example.bios.mvvm.model.Operators;

import java.util.LinkedList;

/**
 * Created by BIOS on 12/20/2016.
 */

public class ValidationArguments {
    private static volatile ValidationArguments validationArguments;

    private ValidationArguments() {
    }

    public static ValidationArguments getInstance() {
        if (validationArguments == null) {
            synchronized (ValidationArguments.class) {
                return validationArguments = new ValidationArguments();
            }
        } else {
            return validationArguments;
        }
    }

    public boolean validate(LinkedList<String> exp, String nextValue) {
        if (!exp.isEmpty()) {
            String value = exp.getLast();

            if ((isContainsDot(value) || isContainsOperator(value)) && isContainsDot(nextValue)) {
                return false;
            }

            if (ifLastIndexIsDot(value) && isContainsOperator(nextValue)) {
                return false;
            }

            if (isContainsOperator(value) && isContainsOperator(nextValue)) {
                return false;
            }

            if (isEqualsZero(value) && isEqualsZero(nextValue)) {
                return false;
            }
        }

        return true;
    }

    public boolean isContainsDot(String value) {
        String dot = ".";
        return value.contains(dot);
    }

    public boolean isContainsOperator(String value) {
        String divide = Operators.DIVIDE.getOperator();
        String multiply = Operators.MULTIPLY.getOperator();
        String subtract = Operators.SUBTRACT.getOperator();
        String plus = Operators.PLUS.getOperator();

        if (value.contains(divide) || value.contains(multiply) ||
                value.contains(subtract) || value.contains(plus)) {
            return true;
        }
        return false;
    }

    public boolean isEqualsZero(String value) {
        if (!value.isEmpty() && value.equals("0")) {
            return true;
        }
        return false;
    }

    public boolean ifLastIndexIsDot(String value) {
        if(!value.isEmpty()) {
            String dot = ".";
            return String.valueOf(value.charAt(value.length() - 1)).equals(dot);
        }
        return false;
    }

    public boolean isFractional(String text) {
        String dot = ".";
        return text.contains(dot);
    }
}
