package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kucharzyk.diabetictoolboxjetpack.R;

import java.util.ArrayList;

public class CalculatorViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<FoodProduct>> mMealSummary;


    public CalculatorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is calculator fragment");

        mMealSummary = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<FoodProduct>> getMealSummary(){
        if (mMealSummary == null) {
            mMealSummary = new MutableLiveData<>();
        }
        return mMealSummary;
    }

    public LiveData<String> getText() {
        return mText;
    }

}
