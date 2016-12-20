package com.example.bios.mvvm.model;

/**
 * Created by BIOS on 12/8/2016.
 */

public enum Operators {
    PLUS("+"), DIVIDE("÷"), MULTIPLY("×"), SUBTRACT("-");

    private String operator;

    Operators(String operator){
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
