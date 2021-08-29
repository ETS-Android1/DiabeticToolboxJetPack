package com.kucharzyk.diabetictoolboxjetpack.room_database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Integer userID;
    private final String username;
    private final Double weight;
    //private String name;
    //private String surname;
    //private String email;
    //private String phoneNumber;
    //private String gender;
    //private Integer age;
    //@Embedded
    //private Address address;
    //@Embedded
    //private MedicalData medicalData;


    public User(String username, Double weight) {
        this.username = username;
        this.weight = weight;
    }

    protected User(Parcel in) {
        if (in.readByte() == 0) {
            userID = null;
        } else {
            userID = in.readInt();
        }
        username = in.readString();
        if (in.readByte() == 0) {
            weight = null;
        } else {
            weight = in.readDouble();
        }
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /*
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setMedicalData(MedicalData medicalData) {
        this.medicalData = medicalData;
    }*/

    public Integer getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (userID == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(userID);
        }
        parcel.writeString(username);
        if (weight == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(weight);
        }
    }

    /*
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

    public Address getAddress() {
        return address;
    }

    public MedicalData getMedicalData() {
        return medicalData;
    }*/
}
/*
class Address {
    private String streetName;
    private String houseNumber;
    private String flatNumber;
    private String cityName;
    private String postalCode;

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
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

    public DiabetesType getDiabetesType() {
        return diabetesType;
    }

    public TreatmentMethod getTreatmentMethod() {
        return treatmentMethod;
    }

    public Integer getYearOfIllness() {
        return yearOfIllness;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Double getHba1c() {
        return hba1c;
    }

    public Integer getInsulinSensitivity() {
        return insulinSensitivity;
    }

    public void setDiabetesType(DiabetesType diabetesType) {
        this.diabetesType = diabetesType;
    }

    public void setTreatmentMethod(TreatmentMethod treatmentMethod) {
        this.treatmentMethod = treatmentMethod;
    }

    public void setYearOfIllness(Integer yearOfIllness) {
        this.yearOfIllness = yearOfIllness;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setHba1c(Double hba1c) {
        this.hba1c = hba1c;
    }

    public void setInsulinSensitivity(Integer insulinSensitivity) {
        this.insulinSensitivity = insulinSensitivity;
    }
}

class DiabetesType {
    private String TYPE1 = "Type 1";
    private String TYPE2 = "Type 2";
    private String MODY = "MODY";
    private String LADA = "LADA";
    private String PREGNANCY = "Pregnancy";
    private String OTHER_DIABETES = "Other";
    private String NO_DIABETES = "No diabetes";

    public String getTYPE1() {
        return TYPE1;
    }

    public String getTYPE2() {
        return TYPE2;
    }

    public String getMODY() {
        return MODY;
    }

    public String getLADA() {
        return LADA;
    }

    public String getPREGNANCY() {
        return PREGNANCY;
    }

    public String getOTHER_DIABETES() {
        return OTHER_DIABETES;
    }

    public String getNO_DIABETES() {
        return NO_DIABETES;
    }

    public void setTYPE1(String TYPE1) {
        this.TYPE1 = TYPE1;
    }

    public void setTYPE2(String TYPE2) {
        this.TYPE2 = TYPE2;
    }

    public void setMODY(String MODY) {
        this.MODY = MODY;
    }

    public void setLADA(String LADA) {
        this.LADA = LADA;
    }

    public void setPREGNANCY(String PREGNANCY) {
        this.PREGNANCY = PREGNANCY;
    }

    public void setOTHER_DIABETES(String OTHER_DIABETES) {
        this.OTHER_DIABETES = OTHER_DIABETES;
    }

    public void setNO_DIABETES(String NO_DIABETES) {
        this.NO_DIABETES = NO_DIABETES;
    }
}

class TreatmentMethod {
    private String PENS = "Injector pen";
    private String PUMP = "Insulin pump";
    private String PILLS = "Pills";
    private String PENS_AND_PUMP = "Injector pen + insulin pump";
    private String DIET = "DIET";
    private String OTHER_TREATMENT = "Other";
    private String NO_TREATMENT = "No treatment";

    public String getPENS() {
        return PENS;
    }

    public String getPUMP() {
        return PUMP;
    }

    public String getPILLS() {
        return PILLS;
    }

    public String getPENS_AND_PUMP() {
        return PENS_AND_PUMP;
    }

    public String getDIET() {
        return DIET;
    }

    public String getOTHER_TREATMENT() {
        return OTHER_TREATMENT;
    }

    public String getNO_TREATMENT() {
        return NO_TREATMENT;
    }

    public void setPENS(String PENS) {
        this.PENS = PENS;
    }

    public void setPUMP(String PUMP) {
        this.PUMP = PUMP;
    }

    public void setPILLS(String PILLS) {
        this.PILLS = PILLS;
    }

    public void setPENS_AND_PUMP(String PENS_AND_PUMP) {
        this.PENS_AND_PUMP = PENS_AND_PUMP;
    }

    public void setDIET(String DIET) {
        this.DIET = DIET;
    }

    public void setOTHER_TREATMENT(String OTHER_TREATMENT) {
        this.OTHER_TREATMENT = OTHER_TREATMENT;
    }

    public void setNO_TREATMENT(String NO_TREATMENT) {
        this.NO_TREATMENT = NO_TREATMENT;
    }
}*/

/*class AccompanyingDiseases {

}*/
