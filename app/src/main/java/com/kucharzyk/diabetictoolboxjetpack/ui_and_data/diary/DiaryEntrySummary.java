package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.time.LocalDate;
import java.util.List;

public class DiaryEntrySummary {

    private final List<MealWithProducts> mealWithProductsList;
    private final LocalDate diaryEntryDate;
    private List<MealProductCrossRef> productsWithServingSizes;

    public DiaryEntrySummary (List<MealWithProducts> mealsWithProducts, LocalDate entryDate) {
        mealWithProductsList = mealsWithProducts;
        diaryEntryDate = entryDate;
    }

    public Double getCarbohydrates() {
        double carbohydrates = 0;
        for (MealWithProducts meal:mealWithProductsList
             ) {
            for (Product product:meal.getProducts()
                 ) {
                carbohydrates += product.getCarbohydrates() * 0.01 * product.getServingSize();
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
                fat += product.getFat() * 0.01 * product.getServingSize();
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
                proteins += product.getProteins() * 0.01 * product.getServingSize();
            }
        }
        return proteins;
    }

    public LocalDate getDiaryEntryDate() {
        return diaryEntryDate;
    }
}
