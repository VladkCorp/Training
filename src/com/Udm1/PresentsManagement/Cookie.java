package com.Udm1.PresentsManagement;

public abstract class Cookie extends Sweet {
    double doughWeight;

    public Cookie(String name, double weight, double sugarWeight, double doughWeight) {
        this.name = name;
        this.weight = weight;
        this.sugarWeight = sugarWeight;
        this.doughWeight = doughWeight;
    }
    public Cookie(){ }

    public void setDoughWeight(double doughWeight) {
        this.doughWeight = doughWeight;
    }
}
