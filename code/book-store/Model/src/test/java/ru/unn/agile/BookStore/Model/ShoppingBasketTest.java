package ru.unn.agile.BookStore.Model;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;

public class ShoppingBasketTest {

    public static final double DELTA = 1e-15;

    @Test
    public void canCreateShoppingBasket() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{1, 0, 0, 0, 0});

        assertNotNull(basket);
    }

    @Test
    public void canGetBasket() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{1, 2, 3, 4, 5});
        int[] newBasket = {1, 2, 3, 4, 5};

        Arrays.equals(newBasket, basket.getShoppingBasket());
    }

    @Test
    public void canCalculatePriceThreeSameBooks() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{0, 0, 0, 3, 0});

        assertEquals(24, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculatePriceFourSameBooks() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{0, 0, 0, 0, 4});

        assertEquals(32, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculatePriceTwoDifferentBooks() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{1, 1, 0, 0, 0});

        assertEquals(15.2, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculatePriceThreeDifferentBooks() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{0, 1, 1, 1, 0});

        assertEquals(21.6, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculatePriceFourDifferentBooks() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{1, 1, 0, 1, 1});

        assertEquals(25.6, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculatePriceFiveDifferentBooks() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{1, 1, 1, 1, 1});

        assertEquals(30, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculateWithDiscountShoppingBasketOne() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{2, 1, 0, 0, 0});

        assertEquals(23.2, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculateWithDiscountShoppingBasketTwo() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{2, 1, 1, 0, 0});

        assertEquals(29.6, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculateWithDiscountShoppingBasketThree() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{3, 2, 0, 1, 1});

        assertEquals(48.8, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculateWithDiscountShoppingBasketFour() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{2, 2, 2, 1, 1});

        assertEquals(51.6, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }

    @Test
    public void canCalculateWithDiscountShoppingBasketFive() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{2, 2, 0, 1, 1});

        assertEquals(40.8, basket.getFinalPriceOfShoppingBasket(), DELTA);
    }
}
