package com.matt.techchallenge;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class SalesTaxAppTest
{
    private ShoppingBasket shoppingBasket;
    private final int salesTaxRate = 10;
    private final int importTaxRate = 5;

    @Test
    public void testInput1EqualsOutput1() {
        WhenIHaveAnEmptyShoppingBasket();
        AndIBuyABook(12.49);
        AndIBuyAMusicCD(14.99);
        AndIBuyAChocolateBar(0.85);
        ThenReceiptShouldBeSameAsOutput1();
    }

    @Test
    public void testInput2EqualsOutput2() {
        WhenIHaveAnEmptyShoppingBasket();
        AndIBuyAnImportedBoxOfChocolates(10.00);
        AndIBuyAnImportedBottleOfPerfume(47.50);
        ThenReceiptShouldBeSameAsOutput2();
    }

    @Test
    public void testInput3EqualsOutput3() {
        WhenIHaveAnEmptyShoppingBasket();
        AndIBuyAnImportedBottleOfPerfume(27.99);
        AndIBuyABottleOfPerfume(18.99);
        AndIBuyAPacketOfHeadachePills(9.75);
        AndIBuyAnImportedBoxOfChocolates(11.25);
        ThenReceiptShouldBeSameAsOutput3();
    }

    private void WhenIHaveAnEmptyShoppingBasket() {
        shoppingBasket = new ShoppingBasket(new ArrayList<SaleItem>(),
                new SalesTaxCalculator(salesTaxRate, importTaxRate),
                new ReceiptPrinter());
    }

    private void AndIBuyABook(double price) {
        shoppingBasket.purchase("book", price, SaleItemType.Book, false);
    }

    private void AndIBuyAMusicCD(double price) {
        shoppingBasket.purchase("music CD", price, SaleItemType.MusicCD, false);
    }

    private void AndIBuyAChocolateBar(double price) {
        shoppingBasket.purchase("chocolate bar", price, SaleItemType.Food, false);
    }

    private void AndIBuyABottleOfPerfume(double price) {
        shoppingBasket.purchase("bottle of perfume", price, SaleItemType.Perfume, false);
    }

    private void AndIBuyAPacketOfHeadachePills(double price) {
        shoppingBasket.purchase("packet of headache pills", price, SaleItemType.MedicalProduct, false);
    }

    private void AndIBuyAnImportedBoxOfChocolates(double price) {
        shoppingBasket.purchase("box of chocolates", price, SaleItemType.Food, true);
    }

    private void AndIBuyAnImportedBottleOfPerfume(double price) {
        shoppingBasket.purchase("bottle of perfume", price, SaleItemType.Perfume, true);
    }

    private void ThenReceiptShouldBeSameAsOutput1() {
        String output1 = "1 book: 12.49 1 music CD: 16.49 1 chocolate bar: 0.85 Sales Taxes: 1.50 Total: 29.83";
        assertEquals(output1, shoppingBasket.printReceipt());
    }

    private void ThenReceiptShouldBeSameAsOutput2() {
        String output2 = "1 imported box of chocolates: 10.50 1 imported bottle of perfume: 54.65 Sales Taxes: 7.65 Total: 65.15";
        assertEquals(output2, shoppingBasket.printReceipt());
    }

    private void ThenReceiptShouldBeSameAsOutput3() {
        String output3 = "1 imported bottle of perfume: 32.19 1 bottle of perfume: 20.89 1 packet of headache pills: 9.75 1 imported box of chocolates: 11.85 Sales Taxes: 6.70 Total: 74.68";
        assertEquals(output3, shoppingBasket.printReceipt());
    }
}
