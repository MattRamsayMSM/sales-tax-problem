package com.matt.techchallenge;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShoppingBasket {
    private final ArrayList<SaleItem> items;
    private final int salesTaxRate = 10;
    private final int importedTaxRate = 5;
    private final DecimalFormat df = new DecimalFormat("0.00");

    private ShoppingBasket(ShoppingBasketBuilder builder) {
        this.items = builder.items;
    }

    public void purchase(String name, double price, SaleItemType type, boolean isImported) {
        switch (type) {
            case Food:
            case Book:
            case MedicalProduct:
                items.add(new SaleItem.SaleItemBuilder(name, price)
                        .isImported(isImported)
                        .isSalesTaxExempt(true).build());
                break;
            case MusicCD:
            case Perfume:
                items.add(new SaleItem.SaleItemBuilder(name, price)
                        .isImported(isImported).build());
                break;
        }
    }

    private double calculateSalesTax(SaleItem item) {
        double salesTax = 0;
        if (!item.isSalesTaxExempt()) {
            salesTax += roundUp(item.getPrice() * (double)salesTaxRate/100, 0.05);
        }
        if (item.isImported()) {
            salesTax += roundUp(item.getPrice() * (double)importedTaxRate/100, 0.05);
        }
        return salesTax;
    }

    public String printReceipt() {
        String receipt = new String();
        double salesTaxTotal = 0;
        double total = 0;
        for (SaleItem item : items) {
            double itemSalesTax = calculateSalesTax(item);
            double itemPriceIncSalesTax = item.getPrice() + itemSalesTax;
            salesTaxTotal += itemSalesTax;
            total += itemPriceIncSalesTax;
            receipt += "1 " + item.getName() + ": " +  df.format(itemPriceIncSalesTax) + " ";
        }
        receipt += "Sales Taxes: " + df.format(salesTaxTotal) + " Total: " + df.format(total);
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
