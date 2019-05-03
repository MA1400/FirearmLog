package com.example.firearmlog;


import java.io.Serializable;


public class Firearm implements Serializable {
    private long id;
    private String typeOfFirearm;
    private String caliber;
    private String roundCount;
    private String rifleName;
    private String weight;
    private String barrelLength;
    private String MOA;

    public Firearm() {
    }

    public Firearm(long id, String typeOfFirearm, String caliber, String roundCount, String rifleName, String weight, String barrelLength, String MOA) {
        this.id = id;
        this.typeOfFirearm = typeOfFirearm;
        this.caliber = caliber;
        this.roundCount = roundCount;
        this.rifleName = rifleName;
        this.weight = weight;
        this.barrelLength = barrelLength;
        this.MOA = MOA;
    }

    public Firearm(String typeOfFirearm, String caliber, String roundCount, String rifleName, String weight, String barrelLength, String MOA) {
        this.typeOfFirearm = typeOfFirearm;
        this.caliber = caliber;
        this.roundCount = roundCount;
        this.rifleName = rifleName;
        this.weight = weight;
        this.barrelLength = barrelLength;
        this.MOA = MOA;
    }

    public Firearm(long id, String typeOfFirearm, String caliber, String roundCount) {
        this.id = id;
        this.typeOfFirearm = typeOfFirearm;
        this.caliber = caliber;
        this.roundCount = roundCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeOfFirearm() {
        return typeOfFirearm;
    }

    public void setTypeOfFirearm(String typeOfFirearm) {
        this.typeOfFirearm = typeOfFirearm;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public void setRoundCount(String roundCount) {
        this.roundCount = roundCount;
    }

    public String getRoundCount() {
        return roundCount;
    }

    public void setRifleName(String rifleName) {
        this.rifleName = rifleName;
    }

    public String getRifleName() {
        return rifleName;
    }

    public void setWeight(String weight) { this.weight = weight; }

    public String getWeight() {
        return weight;
    }

    public void setBarrelLength(String barrelLength) { this.barrelLength = barrelLength; }

    public String getBarrelLength() {
        return barrelLength;
    }

    public void setMOA(String MOA) { this.MOA = MOA; }

    public String getMOA() {
        return MOA;
    }


    @Override
    public String toString() {
        return "Firearm{" +
                "typeOfFirearm='" + typeOfFirearm + '\'' +
                ", caliber='" + caliber + '\'' +
                ", roundCount='" + roundCount + '\'' +
                '}';
    }
}
