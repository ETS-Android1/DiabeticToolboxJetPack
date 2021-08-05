package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

public class ProductSummaryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View child = inflater.inflate(R.layout.fragment_product_summary, container, false);
        return child;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Product currentProduct = ProductSummaryFragmentArgs.fromBundle(getArguments()).getParcelizedProduct();

        TextView mProductName = view.findViewById(R.id.text_product_name);
        TextView mProductCarbohydrates = view.findViewById(R.id.text_product_carbs_value);
        TextView mProductFat = view.findViewById(R.id.text_product_fat_value);
        TextView mProductProteins = view.findViewById(R.id.text_product_proteins_value);
        TextView mProductCarbohydrateExchangerValue = view.findViewById(R.id.text_product_carbs_exchanger_value);
        TextView mProductProteinFatExchangerValue = view.findViewById(R.id.text_product_fat_exchanger_value);
        //EditText mProductServingSize = view.findViewById((R.id.edit_text_serving_size));

        String productName = currentProduct.getProductName();
        Double productServingSize = 1.00;
        Double productCarbohydrates = productServingSize * currentProduct.getCarbohydrates();
        Double productFat = currentProduct.getFat();
        Double productProteins = currentProduct.getProteins();
        Double productCarbohydrateExchangerValue = productCarbohydrates / 12;
        Double productProteinFatExchangerValue = (9 * productFat + 4 * productProteins) / 100;

        mProductName.setText(productName);
        mProductCarbohydrates.setText(String.format(productCarbohydrates.toString()));
        mProductFat.setText(String.format(productFat.toString()));
        mProductProteins.setText(String.format(productProteins.toString()));
        mProductCarbohydrateExchangerValue.setText(String.format(productCarbohydrateExchangerValue.toString()));
        mProductProteinFatExchangerValue.setText(String.format(productProteinFatExchangerValue.toString()));

    }


}
