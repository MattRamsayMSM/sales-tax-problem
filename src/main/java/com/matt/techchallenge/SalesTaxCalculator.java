package com.matt.techchallenge;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SalesTaxCalculator {
    private final int salesTaxRate;
    private final int importedTaxRate;
    private final ArrayList<SaleItem> items;
    private final DecimalFormat df = new DecimalFormat("0.00");

    private SalesTaxCalculator(SalesTaxCalculatorBuilder builder) {
        this.salesTaxRate = builder.salesTaxRate;
        this.importedTaxRate = builder.importTaxRate;
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

    private double calculateSalesTaxForItem(SaleItem item) {
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
            double itemSalesTax = calculateSalesTaxForItem(item);
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

    public static class SalesTaxCalculatorBuilder {
        private int salesTaxRate = 10;
        private int importTaxRate = 5;
        private ArrayList<SaleItem> items;

        public SalesTaxCalculatorBuilder(ArrayList<SaleItem> items) {
            this.items = items;
        }

        public SalesTaxCalculatorBuilder salesTaxRate(int salesTaxRate) {
            this.salesTaxRate = salesTaxRate;
            return this;
        }

        public SalesTaxCalculatorBuilder importTaxRate(int importTaxRate) {
            this.importTaxRate = importTaxRate;
            return this;
        }

        public SalesTaxCalculator build() {
            return new SalesTaxCalculator(this);
        }
    }
}
