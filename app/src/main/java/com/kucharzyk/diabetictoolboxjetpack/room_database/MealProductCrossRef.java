package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Entity;

@Entity(primaryKeys = {"mid", "pid"})
public class MealProductCrossRef {
    private int mid;
    private int pid;

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getMid() {
        return mid;
    }

    public int getPid() {
        return pid;
    }
}


