package com.matt.techchallenge;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Matt on 19/02/14.
 */
public class ReceiptPrinter {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static String printReceipt(ArrayList<SaleItem> saleItems, SalesTaxCalculator salesTaxCalculator) {
        String receipt = new String();
        double salesTaxTotal = 0;
        double total = 0;
        for (SaleItem item : saleItems) {
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
