package com.kucharzyk.diabetictoolboxjetpack.room_database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
@Keep public class Product implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int productId;
    private String productName;
    private Double carbohydrates;
    private Double fat;
    private Double proteins;
    private Double servingSize;

    public Product(@NonNull String productName, Double carbohydrates, Double fat, Double proteins,
                   Double servingSize) {
        this.productName = productName;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.proteins = proteins;
        this.servingSize = servingSize;
    }

    public void setProductId(int productId) { this.productId = productId; }


    public int getProductId() { return productId; }

    public String getProductName() {
        return productName;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public Double getFat() {
        return fat;
    }

    public Double getProteins() {
        return proteins;
    }

    public Double getServingSize() {
        return servingSize;
    }

    //************* Parcelable *****************//

    protected Product(Parcel in) {
        productId = in.readInt();
        productName = in.readString();
        if (in.readByte() == 0) {
            carbohydrates = null;
        } else {
            carbohydrates = in.readDouble();
        }
        if (in.readByte() == 0) {
            fat = null;
        } else {
            fat = in.readDouble();
        }
        if (in.readByte() == 0) {
            proteins = null;
        } else {
            proteins = in.readDouble();
        }
        if (in.readByte() == 0) {
            servingSize = null;
        } else {
            servingSize = in.readDouble();
        }
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeString(productName);
        if (carbohydrates == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(carbohydrates);
        }
        if (fat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(fat);
        }
        if (proteins == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(proteins);
        }
        if (servingSize == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(servingSize);
        }
    }
}
