package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kucharzyk.diabetictoolboxjetpack.R;

public class ProductSummaryFragment extends Fragment {

    private TextView mProductName;

//    public TextView mProductName;
//    public String mNameTemp;
//    private ProductSummaryFragmentArgs

//    public ProductSummaryFragment(String mNameTemp) {
//        this.mNameTemp = mNameTemp;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View child = inflater.inflate(R.layout.fragment_product_summary, container, false);

//        mProductName = child.findViewById(R.id.text_product_name);
//        mProductName.setText(mNameTemp);

        return child;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mProductName = view.findViewById(R.id.text_product_name);
        String productName = ProductSummaryFragmentArgs.fromBundle(getArguments()).getMProductName();
        mProductName.setText(productName);
    }
}
