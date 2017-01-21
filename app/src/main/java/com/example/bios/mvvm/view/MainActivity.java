package com.example.bios.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bios.mvvm.R;
import com.example.bios.mvvm.databinding.MainLayoutBinding;
import com.example.bios.mvvm.viewmodel.CalculatorViewModel;
import com.example.bios.mvvm.viewmodel.Display;
import com.example.bios.mvvm.viewmodel.DisplayHandlers;
import com.example.bios.mvvm.viewmodel.UpperDisplay;

/**
 * Created by BIOS on 12/5/2016.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.main_layout);
        setViewModel(binding);
        setCalculatorDisplay(binding);
        setUpperCalculatorDisplay(binding);
        setCalculatorDisplayHandlers(binding);
    }

    private void setCalculatorDisplayHandlers(MainLayoutBinding binding) {
        DisplayHandlers dispalyHandlers = new DisplayHandlers();
        binding.displayActivity.setDisplayHandlers(dispalyHandlers);
    }

    private void setUpperCalculatorDisplay(MainLayoutBinding binding) {
        UpperDisplay upperDisplay = UpperDisplay.getInstance();
        binding.displayActivity.setUpperDisplay(upperDisplay);
    }

    private void setCalculatorDisplay(MainLayoutBinding binding) {
        Display display = Display.getInstance();
        binding.displayActivity.setDisplay(display);
    }

    private void setViewModel(MainLayoutBinding binding) {
        CalculatorViewModel mainViewModel = new CalculatorViewModel();
        binding.keyboardActivity.setViewModel(mainViewModel);
    }
}
