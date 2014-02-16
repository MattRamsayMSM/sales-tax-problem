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

    private void WhenIHaveAnEmptyShoppingBasket() {
        basket = new ShoppingBasket.ShoppingBasketBuilder(new ArrayList<SaleItem>()).build();
    }

    private void AndIBuyABook() {
        basket.purchase("book", 12.49, SaleItemType.Book);
    }

    private void AndIBuyAMusicCD() {
        basket.purchase("music CD", 14.99, SaleItemType.MusicCD);
    }

    private void AndIBuyAChocolateBar() {
        basket.purchase("chocolate bar", 0.85, SaleItemType.Food);
    }

    private void ThenReceiptShouldBeSameAsOutput1() {
        String output1 = "1 book: 12.49 1 music CD: 16.49 1 chocolate bar: 0.85 Sales Taxes: 1.50 Total: 29.83";
        assertEquals(output1, basket.printReceipt());
    }
}
