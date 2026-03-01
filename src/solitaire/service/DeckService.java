package solitaire.service;

import solitaire.domain.Card;
import solitaire.domain.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckService {

    private final Random random;

    public DeckService(Random random){
        this.random=random;
    }

    public List<Card> generate(){
        List<Card> deck = create();
        shuffle(deck);
        return deck;
    }

    private List<Card> create(){
        List<Card> deck = new ArrayList<>();
        for(var suit: Suit.values()) {
            for(int i=1;i<=13;i++){
                deck.add(new Card(i, suit));
            }
        }
        return deck;
    }

    private void shuffle(List<Card> deck){
        Collections.shuffle(deck, random);
    }

    public void nextCard(List<Card> deck){
        if(deck.isEmpty()){
            throw new IllegalArgumentException();
        }
        var card = deck.remove(deck.size()-1);
        deck.add(0, card);
    }
}
