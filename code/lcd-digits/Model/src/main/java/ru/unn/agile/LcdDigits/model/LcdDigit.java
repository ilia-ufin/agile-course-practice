package ru.unn.agile.LcdDigits.model;

import java.util.Objects;

public class LcdDigit {
    private int number;
    private String lcd;

    public LcdDigit(final int incomingNumber) {
        this.number = incomingNumber;
        this.lcd = "";
    }

    private String convertToLcd(final int incomingNumber) {
        String stringNumber = String.valueOf(incomingNumber);

        String firstLine = "";
        String secondLine = "";
        String thirdLine = "";

        for (char ch: stringNumber.toCharArray()) {
            firstLine = firstLine + toTopOfLcd(ch) + " ";
        }

        for (char ch: stringNumber.toCharArray()) {
            secondLine = secondLine + toMiddleOFLcd(ch) + " ";
        }

        for (char ch: stringNumber.toCharArray()) {
            thirdLine = thirdLine + toBottomOfLcd(ch) + " ";
        }

        return firstLine + "\n" + secondLine + "\n" + thirdLine + "\n";
    }

    private String toTopOfLcd(final char digit) {
        String top = "";
        switch (digit) {
            case '1':
            case '4':
                top = "...";
                break;
            default:
                top = "._.";
                break;
        }
        return top;
    }

    private String toMiddleOFLcd(final char digit) {
        String mid = "";
        switch (digit) {
            case '0':
                mid = "|.|";
                break;
            case '1':
            case '7':
                mid = "..|";
                break;
            case '2':
            case '3':
                mid = "._|";
                break;
            case '5':
            case '6':
                mid = "|_.";
                break;
            default:
                mid = "|_|";
                break;
        }
        return mid;
    }

    private String toBottomOfLcd(final char digit) {
        String bottom = "";
        switch (digit) {
            case '0':
            case '6':
            case '8':
                bottom = "|_|";
                break;
            case '2':
                bottom = "|_.";
                break;
            case '3':
            case '5':
                bottom = "._|";
                break;
            default:
                bottom = "..|";
                break;
        }
        return bottom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, lcd);
    }

    public boolean equals(final Object object) {
        LcdDigit other = (LcdDigit) object;
        return other.getNumber() == getNumber() && other.getLcd().equals(getLcd());
    }

    public void setNumber(final int incomingNumber) {
        this.number = incomingNumber;
    }

    public int getNumber() {
        return number;
    }

    public String getLcd() {
        this.lcd = convertToLcd(number);
        return lcd;
    }
}
