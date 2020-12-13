package com.example.diabetictoolboxjetpack;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String gender;
    private Integer age;
    @Embedded
    private Address address;
    @Embedded
    private MedicalData medicalData;

    public User(String username, String name, String surname, String email, String phoneNumber, String gender, Integer age) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }
}

class Address {
    private String streetName;
    private String houseNumber;
    private String flatNumber;
    private String cityName;
    private String postalCode;
}

class MedicalData {
    @Embedded
    private DiabetesType diabetesType;
    @Embedded
    private TreatmentMethod treatmentMethod;
    private Integer yearOfIllness;
    private Integer height;
    private Integer weight;
    private Double hba1c;
    //private AccompanyingDiseases accompanyingDiseases[];
    private Integer insulinSensitivity;
}

class DiabetesType {
    private final String TYPE1 = "Type 1";
    private final String TYPE2 = "Type 2";
    private final String MODY = "MODY";
    private final String LADA = "LADA";
    private final String PREGNANCY = "Pregnancy";
    private final String OTHER = "Other";
    private final String NONE = "No diabetes";

}

class TreatmentMethod {
    private final String PENS = "Injector pen";
    private final String PUMP = "Insulin pump";
    private final String PILLS = "Pills";
    private final String PENS_AND_PUMP = "Injector pen + insulin pump";
    private final String DIET = "DIET";
    private final String OTHER = "Other";
    private final String NONE = "No diabetes";

}

/*class AccompanyingDiseases {

}*/
