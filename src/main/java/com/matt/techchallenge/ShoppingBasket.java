package com.matt.techchallenge;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Matt on 19/02/14.
 */
public class ShoppingBasket {
    private ArrayList<SaleItem> items;
    private SalesTaxCalculator salesTaxCalculator;
    private final DecimalFormat df = new DecimalFormat("0.00");

    public ShoppingBasket(ArrayList<SaleItem> items, SalesTaxCalculator salesTaxCalculator) {
        this.items = items;
        this.salesTaxCalculator = salesTaxCalculator;
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

    public String printReceipt() {
        String receipt = new String();
        double salesTaxTotal = 0;
        double total = 0;
        for (SaleItem item : items) {
            double itemSalesTax = salesTaxCalculator.calculateSalesTaxForItem(item);
            double itemPriceIncSalesTax = item.getPrice() + itemSalesTax;
            salesTaxTotal += itemSalesTax;
            total += itemPriceIncSalesTax;
            receipt += "1 " + item.getName() + ": " +  df.format(itemPriceIncSalesTax) + " ";
        }
        receipt += "Sales Taxes: " + df.format(salesTaxTotal) + " Total: " + df.format(total);
        return receipt;
    }
}
