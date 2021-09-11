package com.kucharzyk.diabetictoolboxjetpack;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class Globals {
    public static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");

    public static boolean containsProduct(final List<Product> list, final String productName){
        return list.stream().anyMatch(o -> o.getProductName().equals(productName));
    }

    public static boolean containsExercise(final List<Exercise> list, final String exerciseName){
        return list.stream().anyMatch(o -> o.getExerciseName().equals(exerciseName));
    }

    //Bushman B PhD. Complete Guide to Fitness and Health 2nd Edition. American College of Sports Medicine. Human Kinetics. 2017.
    public static Double calculateCaloriesBurned(Double durationInMinutes, Double ExerciseMET, Double weightInKg) {
        return durationInMinutes * (ExerciseMET * 3.5 * weightInKg) / 200.0;
    }

    public static String getMessageDigest(String algorithmName, String messageToDigest) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithmName);
        return Arrays.toString(md.digest(messageToDigest.getBytes(StandardCharsets.UTF_8)));
    }


}
