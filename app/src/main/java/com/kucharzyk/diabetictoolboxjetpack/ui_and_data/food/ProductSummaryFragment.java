package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.food;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.List;
import java.util.Objects;

public class ProductSummaryFragment extends Fragment {
    public static final String TAG = "ProductSummaryFragment";

    private FoodViewModel foodViewModel;
    private NavController navController;
    private Double ratio;

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
    FloatingActionButton mSaveProductButton;
    Product currentProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        foodViewModel = new ViewModelProvider(requireActivity()).get(FoodViewModel.class);
        View child = inflater.inflate(R.layout.product_summary_fragment, container, false);
        navController = NavHostFragment.findNavController(this);
        mProductName = child.findViewById(R.id.text_product_name);
        mProductCarbohydrates = child.findViewById(R.id.text_product_carbs_value);
        mProductFat = child.findViewById(R.id.text_product_fat_value);
        mProductProteins = child.findViewById(R.id.text_product_proteins_value);
        mProductCarbohydrateExchangerValue = child.findViewById(R.id.text_product_carbs_exchanger_value);
        mProductProteinFatExchangerValue = child.findViewById(R.id.text_product_fat_exchanger_value);
        mProductServingSize = child.findViewById(R.id.edit_text_input_serving_size);
        mSaveProductButton = child.findViewById(R.id.button_save_product);
        return child;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        currentProduct = ProductSummaryFragmentArgs.fromBundle(getArguments()).getParcelizedProduct();
        //Integer currentProductPosition = ProductSummaryFragmentArgs.fromBundle(getArguments()).getPosition();

        String productName = currentProduct.getProductName();
        productServingSize = currentProduct.getServingSize();
        ratio = calculateRatio(productServingSize);
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
                } else productServingSize = 100.0;
                getProductAttributes(currentProduct, productServingSize);
                setProductAttributes(productName, productCarbohydrates, productFat, productProteins,
                        productCarbohydrateExchangerValue, productProteinFatExchangerValue);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mSaveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product newProduct = new Product(productName, productCarbohydrates, productFat, productProteins, productServingSize);
                newProduct.setProductId(currentProduct.getProductId());
                List<Product> allProducts = foodViewModel.getMeal();
                if (Globals.containsProduct(allProducts, currentProduct.getProductName())) {
                    boolean isFound = false;
                    for (int i = 0; i < allProducts.size() && !isFound; i++){
                        String productNameToReplace = allProducts.get(i).getProductName();
                        if (productNameToReplace.equals(newProduct.getProductName())){
                            allProducts.set(i, newProduct);
                            isFound = true;
                        }
                    }
                    if (!isFound){
                        try {
                            throw new Exception("Unreachable");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    allProducts.add(newProduct);
                }
                foodViewModel.getMealSummary().setValue(allProducts);
                navController.navigateUp();
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

    private void getProductAttributes(Product currentProduct, Double productServingSize) {

        productCarbohydrates = ratio * productServingSize * currentProduct.getCarbohydrates();
        productFat = ratio * productServingSize * currentProduct.getFat();
        productProteins = ratio * productServingSize * currentProduct.getProteins();
        productCarbohydrateExchangerValue = productCarbohydrates / 12;
        productProteinFatExchangerValue = (9 * productFat + 4 * productProteins) / 100;
    }

    private Double calculateRatio(Double productServingSize) {
        return (100.0 / productServingSize) * 0.01;
    }


}
