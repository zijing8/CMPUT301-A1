package com.example.mycarfootprint;

import java.util.Date;

public class Station {
    private String name;
    private Date date;
    private String type;
    private int amount;
    private double price;

    public Station (String name, Date date, String type, int amount, double price) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.price = price;
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
}
