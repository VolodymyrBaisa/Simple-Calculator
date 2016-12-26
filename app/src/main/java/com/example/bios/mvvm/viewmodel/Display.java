package com.example.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.bios.mvvm.BR;

/**
 * Created by BIOS on 12/8/2016.
 */

public class Display extends BaseObservable {
    private String value;
    private static volatile Display display;

    private Display() {
    }

    public static Display getInstance() {
        if (display == null) {
            synchronized (Display.class) {
                return display = new Display();
            }
        } else {
            return display;
        }
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        display.notifyPropertyChanged(BR.value);
    }

    public boolean isEmpty() {
        return this.value == null || this.value.isEmpty();
    }
}
