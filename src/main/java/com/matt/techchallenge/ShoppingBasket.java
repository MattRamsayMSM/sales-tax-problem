package com.matt.techchallenge;

import java.util.ArrayList;

public class ShoppingBasket {
    public ArrayList<SaleItem> items;

    private ShoppingBasket(ShoppingBasketBuilder builder) {
        this.items = builder.items;
    }

    public void purchase(SaleItem item) {
        items.add(item);
    }

    public String printReceipt() {
        String receipt = new String();
        for (SaleItem item : items) {
            receipt += "1 " + item.toString() + " ";
        }
        return receipt;
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
