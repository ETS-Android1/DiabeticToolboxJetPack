package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CalculatorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is calculator fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
