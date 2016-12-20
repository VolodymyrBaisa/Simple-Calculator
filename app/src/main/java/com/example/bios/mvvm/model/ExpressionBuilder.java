package com.example.bios.mvvm.model;

/**
 * Created by BIOS on 12/13/2016.
 */

public class ExpressionBuilder {
    private static volatile ExpressionBuilder expressionBuilder;
    private final StringBuilder expression = new StringBuilder("0");
    private String lastExpression;

    private ExpressionBuilder() {
    }

    public static ExpressionBuilder getInstatnce() {
        if (expressionBuilder == null) {
            synchronized (ExpressionBuilder.class) {
                return expressionBuilder = new ExpressionBuilder();
            }
        }

        return expressionBuilder;
    }

    public void append(String value) {
        expression.append(replaceAllSeparator(value));
        lastExpression = expression.toString();
    }

    public boolean contains(String value) {
        return expression.indexOf(value) != -1;
    }

    public String savedlastExp() {
        return lastExpression;
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

    private String replaceAllSeparator(String value){
        return value.replaceAll(",", "");
    }
}