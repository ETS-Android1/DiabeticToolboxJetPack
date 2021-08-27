package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DiaryEntrySummary {

    private final List<MealWithProducts> mealWithProductsList;
    private final LocalDate diaryEntryDate;
    private List<MealProductCrossRef> productsWithServingSizes;

    public DiaryEntrySummary (List<MealWithProducts> mealsWithProducts, List<MealProductCrossRef> mealProductCrossRef, LocalDate entryDate) {
        mealWithProductsList = mealsWithProducts;
        productsWithServingSizes = mealProductCrossRef;
        diaryEntryDate = entryDate;
    }

    public Double getCarbohydrates() {
        double carbohydrates = 0;
        for (MealWithProducts meal:mealWithProductsList
             ) {
            for (Product product:meal.getProducts()
                 ) {
                Double servingSize = getServingSizeFromProductId(productsWithServingSizes, product);
                carbohydrates += product.getCarbohydrates() * 0.01 * servingSize;
            }
        }
        return carbohydrates;
    }

    public Double getFat() {
        double fat = 0;
        for (MealWithProducts meal:mealWithProductsList
        ) {
            for (Product product:meal.getProducts()
            ) {
                Double servingSize = getServingSizeFromProductId(productsWithServingSizes, product);
                fat += product.getFat() * 0.01 * servingSize;
            }
        }
        return fat;
    }

    public Double getProteins() {
        double proteins = 0;
        for (MealWithProducts meal:mealWithProductsList
        ) {
            for (Product product:meal.getProducts()
            ) {
                Double servingSize = getServingSizeFromProductId(productsWithServingSizes, product);
                proteins += product.getProteins() * 0.01 * servingSize;
            }
        }
        return proteins;
    }

    public LocalDate getDiaryEntryDate() {
        return diaryEntryDate;
    }

    private Double getServingSizeFromProductId(List<MealProductCrossRef> productsWithServingSizes, Product product) {
        Optional<MealProductCrossRef> optionalCrossRef = productsWithServingSizes.stream().
                filter(x -> x.getProductId() == product.getProductId()).findFirst();
        return optionalCrossRef.isPresent() ? optionalCrossRef.get().getServingSize(): product.getServingSize();
    }
}
