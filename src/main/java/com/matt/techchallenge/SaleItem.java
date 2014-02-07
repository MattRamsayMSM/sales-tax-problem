package com.matt.techchallenge;

public class SaleItem {
    private String name;
    private double price;

    private SaleItem(SaleItemBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return name + " : " + price;
    }

    //Builder Class
    public static class SaleItemBuilder{

        private String name;
        private double price;

        public SaleItemBuilder(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public SaleItem build() {
            return new SaleItem(this);
        }
    }
}
