package com.example.bios.mvvm.viewmodel;

import android.view.View;
import android.widget.Button;

import com.example.bios.mvvm.model.CalculatorModel;
import com.example.bios.mvvm.model.ErrorMessage;
import com.example.bios.mvvm.model.ExpressionBuilder;
import com.example.bios.mvvm.utils.Formatter;
import com.example.bios.mvvm.utils.Parser;

import java.util.LinkedList;

/**
 * Created by BIOS on 12/5/2016.
 */

public class CalculatorViewModel {
    private CalculatorModel calculatorModel = new CalculatorModel();
    private Expression expressionBuilder = ExpressionBuilder.getInstance();
    private static Display display = Display.getInstance();
    private UpperDisplay upperDisplay = UpperDisplay.getInstance();

    public void onClickKeypad(View v) {
        String value = ((Button) v).getText().toString();

        LinkedList<String> expressionList = Parser.parse(expressionBuilder.toString());
        ValidationArguments validationArguments = ValidationArguments.getInstance();

        if (validationArguments.isEqualsZero(expressionBuilder.toString())
                && !validationArguments.isFractional(value) && !validationArguments.isEqualsOperator(value)) {
            expressionBuilder.clear();
        }

        boolean validated = validationArguments.validate(expressionList, value);
        if (validated) expressionBuilder.append(value);

        String upperScreen = Formatter.stringFormat(expressionBuilder.getExpression());
        String lowerScreen = Formatter.stringFormat(expressionBuilder.toString());

        display.setValue(lowerScreen);
        upperDisplay.setValue(upperScreen);
    }

    public void onClickEqual(View v) {
        try {
            LinkedList<String> expressionList = Parser.parse(expressionBuilder.toString());
            String result = Formatter.doubleToString(calculatorModel.calculate(expressionList));
            expressionBuilder.setResult(result);
            display.setValue(result);
        } catch (NumberFormatException e) {
            display.setValue(ErrorMessage.NumberFormatException());
            expressionBuilder.clear();
        }
    }
}
