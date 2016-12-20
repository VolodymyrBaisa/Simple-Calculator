package com.example.bios.mvvm.viewmodel;

import android.view.View;
import android.widget.Button;

import com.example.bios.mvvm.model.CalculatorModel;
import com.example.bios.mvvm.model.ExpressionBuilder;
import com.example.bios.mvvm.model.Operators;

import java.util.LinkedList;

/**
 * Created by BIOS on 12/5/2016.
 */

public class CalculatorViewModel {
    private CalculatorModel calculatorModel = new CalculatorModel();
    private ExpressionBuilder expressionBuilder = ExpressionBuilder.getInstatnce();
    private Display display = Display.getInstance();
    private UpperDisplay upperDisplay = UpperDisplay.getInstance();

    public void onClickKeypad(View v) {
        String buttonText = ((Button) v).getText().toString();
        if (!(isLastIndexOperator(expressionBuilder.toString()) && isLastIndexOperator(buttonText) ||
                isLastIndexDot(expressionBuilder.toString()) && isLastIndexDot(buttonText))) {

            if (isFirstZero(expressionBuilder.toString()) && !isFractional(buttonText)) {
                expressionBuilder.clear();
            }
            expressionBuilder.append(buttonText);

            String upperScreen = Formatter.stringFormat(expressionBuilder.savedlastExp());
            String lowerScreen = Formatter.stringFormat(expressionBuilder.toString());

            display.setValue(upperScreen);
            upperDisplay.setValue(lowerScreen);
        }
    }

    public void onClickEqual(View v) {
        try {
            LinkedList<String> expressionList = Parser.parse(expressionBuilder.toString());
            String result = Formatter.doubleToString(calculatorModel.calculate(expressionList));
            display.setValue(result);

            expressionBuilder.clear();
            expressionBuilder.append(result);
        } catch (NumberFormatException e) {
            display.setValue(ErrorMessage.NumberFormatException());
            expressionBuilder.clear();
        }
    }

    private boolean isLastIndexDot(String exp) {
        if (exp.length() != 0) {
            String dot = ".";
            String lastIndex = String.valueOf(exp.charAt(exp.length() - 1));
            return lastIndex.equals(dot);
        }

        return false;
    }

    private boolean isLastIndexOperator(String exp) {
        if (exp.length() != 0) {
            String divide = Operators.DIVIDE.getOperator();
            String multiply = Operators.MULTIPLY.getOperator();
            String subtract = Operators.SUBTRACT.getOperator();
            String plus = Operators.PLUS.getOperator();

            String lastIndex = String.valueOf(exp.charAt(exp.length() - 1));

            if (lastIndex.equals(divide) || lastIndex.equals(multiply) ||
                    lastIndex.equals(subtract) || lastIndex.equals(plus)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFractional(String text) {
        if (text.contains(".")) {
            return true;
        }
        return false;
    }

    private boolean isFirstZero(String exp) {
        if (!exp.isEmpty() && exp.equals("0")) {
            return true;
        }
        return false;
    }
}
