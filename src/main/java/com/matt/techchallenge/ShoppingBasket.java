package com.matt.techchallenge;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShoppingBasket {
    private final ArrayList<SaleItem> items;
    private final int salesTaxRate = 10;
    private final DecimalFormat df = new DecimalFormat("#.00");

    private ShoppingBasket(ShoppingBasketBuilder builder) {
        this.items = builder.items;
    }

    public void purchase(String name, double price, SaleItemType type) {
        //Is item taxable
        switch (type) {
            case Food:
            case Book:
            case MedicalProduct:
                items.add(new SaleItem.SaleItemBuilder(name, price).isSalesTaxExempt(true).build());
                break;
            case MusicCD:
            case Perfume:
                items.add(new SaleItem.SaleItemBuilder(name, price).build());
                break;
        }
    }

    private double calculateSalesTax() {
        double salesTaxTotal = 0;
        for(SaleItem item : items) {
            if (!item.isSalesTaxExempt()) {
                System.out.println(item.getPrice());
                salesTaxTotal += roundUp(item.getPrice() * (double)salesTaxRate/100, 0.05);
            }
        }
        return salesTaxTotal;
    }

    public String printReceipt() {
        String receipt = new String();
        for (SaleItem item : items) {
            receipt += "1 " + item.toString() + " ";
        }
        receipt += "Sales Taxes: " + df.format(calculateSalesTax());
        return receipt;
    }

    private static double roundUp(double number, double roundUpBy) {
        double multiplier = (1 / roundUpBy);
        return Math.ceil(number * multiplier) / multiplier;
    }

    //Builder Class
    public static class ShoppingBasketBuilder{
        private ArrayList<SaleItem> items;

        public ShoppingBasketBuilder(ArrayList<SaleItem> items) {
            this.items = items;
        }

        public ShoppingBasket build() {
            return new ShoppingBasket(this);
        }
    }
}
