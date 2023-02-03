package com.example.mycarfootprint;

import java.util.Date;

public class Station {
    private String name;
    private Date date;
    private String type;
    private int amount;
    private double price;
    private double cost;
    private int footprint;

    public Station (String name, Date date, String type, int amount, double price) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.cost = Math.round((price * amount) * 100.0) / 100.0;
        if (type.equals("Gasoline")) {
            this.footprint = (int)(2.32 * amount);
        } else {
            this.footprint = (int)(2.69 * amount);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getFootprint() {
        return footprint;
    }

    public void setFootprint(int footprint) {
        this.footprint = footprint;
    }
}
