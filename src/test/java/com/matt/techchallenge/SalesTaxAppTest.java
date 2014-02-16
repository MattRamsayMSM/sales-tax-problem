package com.matt.techchallenge;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class SalesTaxAppTest
{
    private ShoppingBasket basket;

    @Test
    public void testInput1EqualsOutput1() {
        WhenIHaveAnEmptyShoppingBasket();
        AndIBuyABook();
        AndIBuyAMusicCD();
        AndIBuyAChocolateBar();
        ThenReceiptShouldBeSameAsOutput1();
    }

    @Test
    public void testInput2EqualsOutput2() {
        WhenIHaveAnEmptyShoppingBasket();
        AndIBuyAnImportedBoxOfChocolates();
        AndIBuyAnImportedBottleOfPerfume();
        ThenReceiptShouldBeSameAsOutput2();
    }

    private void WhenIHaveAnEmptyShoppingBasket() {
        basket = new ShoppingBasket.ShoppingBasketBuilder(new ArrayList<SaleItem>()).build();
    }

    private void AndIBuyABook() {
        basket.purchase("book", 12.49, SaleItemType.Book, false);
    }

    private void AndIBuyAMusicCD() {
        basket.purchase("music CD", 14.99, SaleItemType.MusicCD, false);
    }

    private void AndIBuyAChocolateBar() {
        basket.purchase("chocolate bar", 0.85, SaleItemType.Food, false);
    }

    private void AndIBuyAnImportedBoxOfChocolates() {
        basket.purchase("box of chocolates", 10.00, SaleItemType.Food, true);
    }

    private void AndIBuyAnImportedBottleOfPerfume() {
        basket.purchase("bottle of perfume", 47.50, SaleItemType.Perfume, true);
    }

    private void ThenReceiptShouldBeSameAsOutput1() {
        String output1 = "1 book: 12.49 1 music CD: 16.49 1 chocolate bar: 0.85 Sales Taxes: 1.50 Total: 29.83";
        assertEquals(output1, basket.printReceipt());
    }

    private void ThenReceiptShouldBeSameAsOutput2() {
        String output2 = "1 imported box of chocolates: 10.50 1 imported bottle of perfume: 54.65 Sales Taxes: 7.65 Total: 65.15";
        assertEquals(output2, basket.printReceipt());
    }
}
