package com.example.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.bios.mvvm.BR;

/**
 * Created by BIOS on 12/14/2016.
 */

public class UpperDisplay extends BaseObservable {
    private String value;
    private static volatile UpperDisplay upperDisplay;

    private UpperDisplay() {
    }

    public static UpperDisplay getInstance() {
        if (upperDisplay == null) {
            synchronized (UpperDisplay.class) {
                return upperDisplay = new UpperDisplay();
            }
        } else {
            return upperDisplay;
        }
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        upperDisplay.notifyPropertyChanged(BR.value);
    }

    public boolean isEmpty() {
        return this.value == null || this.value.isEmpty();
    }
}
