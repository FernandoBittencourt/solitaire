package solitaire.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Card> deck;
    private BoardStack stacks;
    private List<List<Card>> lists;

    public Board(List<Card> deck){
        this.deck = deck;
        stacks = new BoardStack();
        lists = new ArrayList<>();
        for(int i=0; i<7;i++){
            lists.add(new ArrayList<>());
        }
    }

    public Board(List<Card> deck, BoardStack stacks, List<List<Card>> lists){
        this.deck = deck;
        this.stacks = stacks;
        this.lists = lists;
    }
}
