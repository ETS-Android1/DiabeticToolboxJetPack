package com.kucharzyk.diabetictoolboxjetpack.room_database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int mPid;
    private String mProductName;
    private Double mCarbohydrates;
    private Double mFat;
    private Double mProteins;
    private Double mServingSize;

    public Product(@NonNull String productName, Double carbohydrates, Double fat, Double proteins,
                   Double servingSize) {
        this.mProductName = productName;
        this.mCarbohydrates = carbohydrates;
        this.mFat = fat;
        this.mProteins = proteins;
        this.mServingSize = servingSize;
    }

    public void setPid(int mPid) { this.mPid = mPid; }


    public int getPid() { return mPid; }

    public String getProductName() {
        return mProductName;
    }

    public Double getCarbohydrates() {
        return mCarbohydrates;
    }

    public Double getFat() {
        return mFat;
    }

    public Double getProteins() {
        return mProteins;
    }

    public Double getServingSize() {
        return mServingSize;
    }

    //************* Parcelable *****************//

    protected Product(Parcel in) {
        mPid = in.readInt();
        mProductName = in.readString();
        if (in.readByte() == 0) {
            mCarbohydrates = null;
        } else {
            mCarbohydrates = in.readDouble();
        }
        if (in.readByte() == 0) {
            mFat = null;
        } else {
            mFat = in.readDouble();
        }
        if (in.readByte() == 0) {
            mProteins = null;
        } else {
            mProteins = in.readDouble();
        }
        if (in.readByte() == 0) {
            mServingSize = null;
        } else {
            mServingSize = in.readDouble();
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
        dest.writeInt(mPid);
        dest.writeString(mProductName);
        if (mCarbohydrates == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mCarbohydrates);
        }
        if (mFat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mFat);
        }
        if (mProteins == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mProteins);
        }
        if (mServingSize == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mServingSize);
        }
    }
}
