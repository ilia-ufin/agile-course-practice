package ru.unn.agile.caesarcipher.model;

public final class CaesarCipher {
    private CaesarCipher() {
        /* constructor not needed here */
    }

    private static final int NUMBER_OF_CHARACTERS_IN_INTERVAL = (int) 'z' - (int) 'a' + 1;
    private static int calculateStartingPosition(final int charCode) {
        int startingPosition = 0;
        if ((charCode >= (int) 'a') & (charCode <= (int) 'z')) {
            startingPosition = (int) 'a';
        } else if ((charCode >= (int) 'A') & (charCode <= (int) 'Z')) {
            startingPosition = (int) 'A';
        }
        return startingPosition;
    }

    public static String encode(final String input, final int offset) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            int charCode = (int) input.charAt(i);
            int startingPosition = calculateStartingPosition(charCode);
            if (startingPosition != 0) {
                int charPositionInAlphabet = charCode - startingPosition;
                int newCharPositionInAlphabet = charPositionInAlphabet + offset;
                newCharPositionInAlphabet %= NUMBER_OF_CHARACTERS_IN_INTERVAL;
                if (newCharPositionInAlphabet < 0) {
                    newCharPositionInAlphabet += NUMBER_OF_CHARACTERS_IN_INTERVAL;
                }
                int newCharCode = newCharPositionInAlphabet + startingPosition;
                output += (char) (newCharCode);
            } else {
                output += (char) (charCode);
            }
        }
        return output;
    }

    public static String decode(final String input, final int offset) {
        return encode(input, -1 * offset);
    }
}

