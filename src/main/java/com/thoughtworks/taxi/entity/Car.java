package com.thoughtworks.taxi.entity;

import java.util.Date;

public class Car  implements Comparable<Car>{
    private String carNumber;
    private Date submitDate;
    private Date boughtDate;
    private String brand;
    private int kilos;
    private boolean fixed;

    public Car(){ }
    public Car(String carNumber, Date submitDate, Date boughtDate, String brand, int kilos, boolean fixed){
        this.carNumber = carNumber;
        this.submitDate = submitDate;
        this.boughtDate = boughtDate;
        this.brand = brand;
        this.kilos = kilos;
        this.fixed = fixed;
    }
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(Date boughtDate) {
        this.boughtDate = boughtDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getKilos() {
        return kilos;
    }

    public void setKilos(int kilos) {
        this.kilos = kilos;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public boolean equals(Object o) {
        if (o instanceof Car) {
            if (carNumber.equals(((Car) o).getCarNumber()) && submitDate.equals(((Car) o).getSubmitDate())
                    && boughtDate.equals(((Car) o).getBoughtDate()) && brand.equals(((Car) o).getBrand())
                    && kilos == ((Car) o).getKilos() && fixed == ((Car) o).isFixed()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Car o) {
        return this.brand.compareTo(o.brand);
    }
}
