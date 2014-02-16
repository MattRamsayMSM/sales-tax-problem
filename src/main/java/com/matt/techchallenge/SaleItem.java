package com.matt.techchallenge;

public class SaleItem {
    private final String name;
    private final double price;
    private final boolean isSalesTaxExempt;
    private final boolean isImported;

    private SaleItem(SaleItemBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.isSalesTaxExempt = builder.isSalesTaxExempt;
        this.isImported = builder.isImported;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public boolean isSalesTaxExempt() {
        return isSalesTaxExempt;
    }

    public static class SaleItemBuilder{
        private String name;
        private double price;
        private boolean isSalesTaxExempt = false;

        private boolean isImported = false;

        public SaleItemBuilder(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public SaleItemBuilder isSalesTaxExempt(boolean isSalesTaxExempt) {
            this.isSalesTaxExempt = isSalesTaxExempt;
            return this;
        }

        public SaleItemBuilder isImported(boolean isImported) {
            this.isImported = isImported;
            return this;
        }

        public SaleItem build() {
            return new SaleItem(this);
        }
    }
}
