package com.matt.techchallenge;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Unit test for simple SalesTaxApp.
 */
public class SalesTaxAppTest extends TestCase
{
    private ShoppingBasket basket;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SalesTaxAppTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SalesTaxAppTest.class );
    }

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
        SaleItem book = new SaleItem.SaleItemBuilder("book", 12.49).build();
        basket.purchase(book);
    }

    private void AndIBuyAMusicCD() {
        SaleItem musicCd = new SaleItem.SaleItemBuilder("music CD", 14.99).build();
        basket.purchase(musicCd);
    }

    private void AndIBuyAChocolateBar() {
        SaleItem chocolateBar = new SaleItem.SaleItemBuilder("chocolate bar", 0.85).build();
        basket.purchase(chocolateBar);
    }

    private void ThenReceiptShouldBeSameAsOutput1() {
        String output1 = "1 book : 12.49 1 music CD: 16.49 1 chocolate bar: 0.85 Sales Taxes: 1.50 Total: 29.83";
        assertEquals(output1, basket.printReceipt());
    }
}
