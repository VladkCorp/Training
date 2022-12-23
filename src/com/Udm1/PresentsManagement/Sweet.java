package com.Udm1.PresentsManagement;

public abstract class Sweet {
    String name;
    double weight;
    double sugarWeight;

    public Sweet(String name, double weight, double sugarWeight) {
        this.name = name;
        this.weight = weight;
        this.sugarWeight = sugarWeight;
    }
    public Sweet(){ }

    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setSugarWeight(double sugarWeight) {
        this.sugarWeight = sugarWeight;
    }
}
