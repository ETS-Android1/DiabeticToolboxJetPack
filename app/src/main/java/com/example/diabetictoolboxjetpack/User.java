package com.example.diabetictoolboxjetpack;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    private Integer uid;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Integer age;

}
