package com.example.bios.mvvm.viewmodel;

import android.view.View;

import com.example.bios.mvvm.model.ExpressionBuilder;

/**
 * Created by BIOS on 12/13/2016.
 */

public class DisplayHandlers {
    public boolean onLongClickDisplay(View v) {
        Display display = Display.getInstance();
        UpperDisplay upperDisplay = UpperDisplay.getInstance();

        ExpressionBuilder expressionBuilder = ExpressionBuilder.getInstatnce();
        expressionBuilder.reset();
        display.setValue(expressionBuilder.toString());
        upperDisplay.setValue("");
        return true;
    }

    public void onClickDisplay(View v){
        Display display = Display.getInstance();
        UpperDisplay upperDisplay = UpperDisplay.getInstance();

        ExpressionBuilder expressionBuilder = ExpressionBuilder.getInstatnce();
        expressionBuilder.delete();

        display.setValue(expressionBuilder.toString());
        upperDisplay.setValue(expressionBuilder.getExpression());
    }
}
