package solitaire.repository;

import solitaire.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class DeckLoader {

    public static List<Card> getDeck(List<String> lines) {
        var deck = new ArrayList<Card>();
        var deckString = lines.get(0);
        if ("-".equals(deckString)) {
            return deck;
        }
        var cards = deckString.split(" ");
        for (var card : cards) {
            deck.add(CardLoader.getCard(card));
        }
        return deck;
    }
}