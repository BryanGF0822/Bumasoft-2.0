package model;

import java.io.*;

public class Apartments implements Serializable   {
    private String realStateRegistration;
    private String apartmentNumber;
    private String tower;
    private String ownerId;
    private String state;
    private int population;
    private String residentType;


    public Apartments(String realStateRegistration, String apartmentNumber, String tower, String ownerId, String state, int population, String residentType) throws FileNotFoundException {
        this.realStateRegistration = realStateRegistration;
        this.apartmentNumber = apartmentNumber;
        this.tower = tower;
        this.ownerId = ownerId;
        this.state = state;
        this.population = population;

    }

    public String getRealStateRegistration() {
        return realStateRegistration;
    }

    public void setRealStateRegistration(String realStateRegistration) {
        this.realStateRegistration = realStateRegistration;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getResidentType() {
        return residentType;
    }

    public void setResidentType(String residentType) {
        this.residentType = residentType;
    }





}
