package com.example.bios.mvvm.model;

import java.util.LinkedList;

/**
 * Created by BIOS on 12/8/2016.
 */

public class CalculatorModel {
    public Double calculate(LinkedList<String> exp) throws ArithmeticException, NumberFormatException {

        MathOperations mathOperations = new MathOperations();
        mathOperations.addExpression(exp);
        return mathOperations.result();
    }
}
