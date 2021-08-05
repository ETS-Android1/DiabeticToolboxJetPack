package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.Objects;

public class ProductSummaryFragment extends Fragment {

    private Double productCarbohydrates;
    private Double productFat;
    private Double productProteins;
    private Double productCarbohydrateExchangerValue;
    private Double productProteinFatExchangerValue;
    private Double productServingSize;

    TextView mProductName;
    TextView mProductCarbohydrates;
    TextView mProductFat;
    TextView mProductProteins;
    TextView mProductCarbohydrateExchangerValue;
    TextView mProductProteinFatExchangerValue;
    TextInputEditText mProductServingSize;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View child = inflater.inflate(R.layout.fragment_product_summary, container, false);
        mProductName = child.findViewById(R.id.text_product_name);
        mProductCarbohydrates = child.findViewById(R.id.text_product_carbs_value);
        mProductFat = child.findViewById(R.id.text_product_fat_value);
        mProductProteins = child.findViewById(R.id.text_product_proteins_value);
        mProductCarbohydrateExchangerValue = child.findViewById(R.id.text_product_carbs_exchanger_value);
        mProductProteinFatExchangerValue = child.findViewById(R.id.text_product_fat_exchanger_value);
        mProductServingSize = child.findViewById(R.id.edit_text_input_serving_size);
        return child;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Product currentProduct = ProductSummaryFragmentArgs.fromBundle(getArguments()).getParcelizedProduct();

        String productName = currentProduct.getProductName();
        productServingSize = 1.00;
        getProductAttributes(currentProduct, productServingSize);
        setProductAttributes(productName, productCarbohydrates, productFat, productProteins,
                productCarbohydrateExchangerValue, productProteinFatExchangerValue);

        mProductServingSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    productServingSize = Double.parseDouble(Objects.requireNonNull(mProductServingSize.getText()).toString());
                }
                else productServingSize = 1.00;
                getProductAttributes(currentProduct, productServingSize);
                setProductAttributes(productName, productCarbohydrates, productFat, productProteins,
                        productCarbohydrateExchangerValue, productProteinFatExchangerValue);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void setProductAttributes(String productName, Double productCarbohydrates,
                                      Double productFat, Double productProteins,
                                      Double productCarbohydrateExchangerValue,
                                      Double productProteinFatExchangerValue) {
        mProductName.setText(productName);
        mProductCarbohydrates.setText(Globals.REAL_FORMATTER.format((productCarbohydrates)));
        mProductFat.setText(Globals.REAL_FORMATTER.format(productFat));
        mProductProteins.setText(Globals.REAL_FORMATTER.format(productProteins));
        mProductCarbohydrateExchangerValue.setText(Globals.REAL_FORMATTER.format(productCarbohydrateExchangerValue));
        mProductProteinFatExchangerValue.setText(Globals.REAL_FORMATTER.format(productProteinFatExchangerValue));
    }

    private void getProductAttributes(Product currentProduct, Double productServingSize){
        productCarbohydrates = productServingSize * currentProduct.getCarbohydrates();
        productFat = productServingSize * currentProduct.getFat();
        productProteins = productServingSize * currentProduct.getProteins();
        productCarbohydrateExchangerValue = productCarbohydrates / 12;
        productProteinFatExchangerValue = (9 * productFat + 4 * productProteins) / 100;
    }


}
