package solitaire.repository;

import solitaire.domain.Card;
import solitaire.domain.Suit;

public class CardLoader {

    public static Card getCard(String symbol) {
        char suitChar = symbol.charAt(symbol.length() - 1);
        String valuePart = symbol.substring(0, symbol.length() - 1);

        int value = switch (valuePart) {
            case "A" -> 1;
            case "J" -> 11;
            case "Q" -> 12;
            case "K" -> 13;
            default -> Integer.parseInt(valuePart);
        };

        Suit suit = switch (suitChar) {
            case '♥' -> Suit.HEART;
            case '♦' -> Suit.DIAMOND;
            case '♣' -> Suit.CLUB;
            default -> Suit.SPADE;
        };

        return new Card(value, suit);
    }
}