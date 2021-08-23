package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.time.LocalDate;
import java.util.List;

public class DiaryEntrySummary {

    private List<MealWithProducts> mealWithProductsList;
    private LocalDate diaryEntryDate;

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
                carbohydrates += product.getCarbohydrates();
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
                fat += product.getFat();
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
                proteins += product.getProteins();
            }
        }
        return proteins;
    }

    public LocalDate getDiaryEntryDate() {
        return diaryEntryDate;
    }
}
