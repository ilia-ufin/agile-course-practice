package ru.unn.agile.BookStore.Model;

import java.util.*;

public class ShoppingBasket {
    private int[] booksQuantity;
    private double totalPrice;

    public static final double INITIAL_BOOK_PRICE = 8;
    public static final double TWO_BOOKS_DISCOUNTED = 15.2;
    public static final double THREE_BOOKS_DISCOUNTED = 21.6;
    public static final double FOUR_BOOKS_DISCOUNTED = 25.6;
    public static final double FIVE_BOOKS_DISCOUNTED = 30;
    public static final int ONE_KIND_OF_BOOKS = 1;
    public static final int TWO_KIND_OF_BOOKS = 2;
    public static final int THREE_KIND_OF_BOOKS = 3;
    public static final int FOUR_KIND_OF_BOOKS = 4;
    public static final int FIVE_KIND_OF_BOOKS = 5;

    private static Map<Integer, Double> discountedprices = new HashMap<>();

    static {
        discountedprices.put(ONE_KIND_OF_BOOKS, INITIAL_BOOK_PRICE);
        discountedprices.put(TWO_KIND_OF_BOOKS, TWO_BOOKS_DISCOUNTED);
        discountedprices.put(THREE_KIND_OF_BOOKS, THREE_BOOKS_DISCOUNTED);
        discountedprices.put(FOUR_KIND_OF_BOOKS, FOUR_BOOKS_DISCOUNTED);
        discountedprices.put(FIVE_KIND_OF_BOOKS, FIVE_BOOKS_DISCOUNTED);
    }

    public ShoppingBasket() {
        this.booksQuantity = new int[]{0, 0, 0, 0, 0};
        this.totalPrice = 0.00;
    }

    private void setCountOfBooks(final int numberOfBook, final int count) {
        booksQuantity[numberOfBook] = count;
    }

    public void assignmentShoppingBasket(final int[] booksInBasket) {
        for (int i = 0; i < FIVE_KIND_OF_BOOKS; i++) {
            setCountOfBooks(i, booksInBasket[i]);
        }
    }

    public int[] getShoppingBasket() {
        return booksQuantity;
    }

    public double getShoppingBasketPriceSameBooks() {
        int quantity = 0;

        for (int i = 0; i < FIVE_KIND_OF_BOOKS; i++) {
            if (this.booksQuantity[i] != 0) {
                quantity = booksQuantity[i];
                break;
            }
        }
        this.totalPrice = INITIAL_BOOK_PRICE * quantity;
        return totalPrice;
    }

    private double getStandardDiscount(final int quantity) {
        return discountedprices.get(quantity);
    }

    private int getUniqueBooksInShoppingBasket() {
        int quantityUnique = 0;

        for (int i = 0; i < FIVE_KIND_OF_BOOKS; i++) {
            if (this.booksQuantity[i] != 0) {
                quantityUnique++;
            }
        }
        return quantityUnique;
    }

    public double getFinalPriceOfShoppingBasket() {
        int quantityUnique = getUniqueBooksInShoppingBasket();
        double finalPrice = 0.00;

        if (quantityUnique == ONE_KIND_OF_BOOKS) {
            finalPrice = getShoppingBasketPriceSameBooks();
        } else {
            while (quantityUnique > 0) {
                for (int i = 0; i < FIVE_KIND_OF_BOOKS; i++) {
                    if (this.booksQuantity[i] > 0) {
                        this.booksQuantity[i] -= 1;
                    }
                }
                finalPrice += getStandardDiscount(quantityUnique);
                quantityUnique = getUniqueBooksInShoppingBasket();
            }
        }
        return finalPrice;
    }
}
