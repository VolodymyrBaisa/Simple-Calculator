package com.example.bios.mvvm.model;

import com.example.bios.mvvm.viewmodel.Expression;

/**
 * Created by BIOS on 12/13/2016.
 */

public class ExpressionBuilder implements Expression {
    private static volatile ExpressionBuilder expressionBuilder;
    private final StringBuilder expression = new StringBuilder("0");
    private StringBuilder oldExpression;

    private ExpressionBuilder() {
        oldExpression = new StringBuilder(expression);
    }

    public static ExpressionBuilder getInstance() {
        if (expressionBuilder == null) {
            synchronized (ExpressionBuilder.class) {
                return expressionBuilder = new ExpressionBuilder();
            }
        }
        return expressionBuilder;
    }

    public void append(String value) {
        expression.append(replaceAllSeparator(value));
        oldExpression = new StringBuilder(expression);
    }

    public void setResult(String value){
        clear();
        expression.append(replaceAllSeparator(value));
    }

    public String getExpression() {
        return oldExpression.toString();
    }

    public void delete() {
        if (expression.length() != 0) {
            expression.delete(expression.length() - 1, expression.length());
        }

        if(oldExpression.length() != 0){
            oldExpression.delete(oldExpression.length() - 1, oldExpression.length());
        }
    }

    public void clear() {
        expression.delete(0, expression.length());
        expression.setLength(0);
    }

    public void reset() {
        clear();
        expression.append("0");
    }

    @Override
    public String toString() {
        return expression.toString();
    }

    private String replaceAllSeparator(String value) {
        return value.replaceAll(",", "");
    }
}