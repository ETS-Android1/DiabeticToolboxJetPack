package com.kucharzyk.diabetictoolboxjetpack.room_database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Exercises")
@Keep public class Exercise implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int exerciseId;
    private final String exerciseName;
    private final Double metabolicEquivalentOfTask;
    private final Double exerciseDuration;

    public Exercise(String exerciseName, Double metabolicEquivalentOfTask, Double exerciseDuration) {
        this.exerciseName = exerciseName;
        this.metabolicEquivalentOfTask = metabolicEquivalentOfTask;
        this.exerciseDuration = exerciseDuration;
    }

    protected Exercise(Parcel in) {
        exerciseId = in.readInt();
        exerciseName = in.readString();
        if (in.readByte() == 0) {
            metabolicEquivalentOfTask = null;
        } else {
            metabolicEquivalentOfTask = in.readDouble();
        }
        if (in.readByte() == 0) {
            exerciseDuration = null;
        } else {
            exerciseDuration = in.readDouble();
        }
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public Double getMetabolicEquivalentOfTask() {
        return metabolicEquivalentOfTask;
    }

    public Double getExerciseDuration() {
        return exerciseDuration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(exerciseId);
        parcel.writeString(exerciseName);
        if (metabolicEquivalentOfTask == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(metabolicEquivalentOfTask);
        }
        if (exerciseDuration == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(exerciseDuration);
        }
    }
}
