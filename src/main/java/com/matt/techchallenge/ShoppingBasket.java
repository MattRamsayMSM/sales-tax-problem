package com.matt.techchallenge;

import java.util.ArrayList;

/**
 * Created by Matt on 19/02/14.
 */
public class ShoppingBasket {
    private ArrayList<SaleItem> saleItems;
    private SalesTaxCalculator salesTaxCalculator;
    private ReceiptPrinter receiptPrinter;

    public ShoppingBasket(ArrayList<SaleItem> saleItems, SalesTaxCalculator salesTaxCalculator, ReceiptPrinter receiptPrinter) {
        this.saleItems = saleItems;
        this.salesTaxCalculator = salesTaxCalculator;
        this.receiptPrinter = receiptPrinter;
    }

    public void purchase(String name, double price, SaleItemType type, boolean isImported) {
        switch (type) {
            case Food:
            case Book:
            case MedicalProduct:
                saleItems.add(new SaleItem.SaleItemBuilder(name, price)
                        .isImported(isImported)
                        .isSalesTaxExempt(true).build());
                break;
            case MusicCD:
            case Perfume:
                saleItems.add(new SaleItem.SaleItemBuilder(name, price)
                        .isImported(isImported).build());
                break;
        }
    }

    public String printReceipt() {
        return receiptPrinter.printReceipt(saleItems, salesTaxCalculator);
    }
}
