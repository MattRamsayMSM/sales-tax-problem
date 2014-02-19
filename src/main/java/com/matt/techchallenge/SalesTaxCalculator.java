package com.matt.techchallenge;

public class SalesTaxCalculator {
    private int salesTaxRate;
    private int importTaxRate;

    public SalesTaxCalculator(int salesTaxRate, int importTaxRate) {
        this.salesTaxRate = salesTaxRate;
        this.importTaxRate = importTaxRate;
    }

    public double calculateSalesTaxForItem(SaleItem item) {
        double salesTax = 0;
        if (!item.isSalesTaxExempt()) {
            salesTax += roundUp(item.getPrice() * (double)salesTaxRate/100, 0.05);
        }
        if (item.isImported()) {
            salesTax += roundUp(item.getPrice() * (double) importTaxRate /100, 0.05);
        }
        return salesTax;
    }

    private static double roundUp(double number, double roundUpBy) {
        double multiplier = (1 / roundUpBy);
        return Math.ceil(number * multiplier) / multiplier;
    }
}
