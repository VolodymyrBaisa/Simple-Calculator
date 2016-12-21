package com.example.bios.mvvm.viewmodel;

/**
 * Created by BIOS on 12/20/2016.
 */

public interface Expression {
    void append(String value);

    void setResult(String value);

    String getExpression();

    void delete();

    void clear();

    void reset();

    String toString();
}
