package com.bwie.asus.shopping;

/**
 * Created by ASUS on 2017/10/19.
 */

public class Bean {
    private String name;
    private int price;
    private Boolean b;

    public Bean(String name, int price, Boolean b) {
        this.name = name;
        this.price = price;
        this.b = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", b=" + b +
                '}';
    }
}
