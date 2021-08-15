package com.kucharzyk.diabetictoolboxjetpack;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class Globals {
    public static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");

    public static boolean containsProduct(final List<Product> list, final String productName){
        return list.stream().anyMatch(o -> o.getProductName().equals(productName));
    }


}
