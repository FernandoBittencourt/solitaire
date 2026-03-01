package solitaire.domain;

import java.util.List;

public class Board {
    private List<Card> deck;
    private BoardStack stacks;
    private List<List<Card>> lists;

    public Board(List<Card> deck, List<List<Card>> lists){
        this.deck = deck;
        this.stacks = new BoardStack();
        this.lists = lists;
    }

    public Board(List<Card> deck, BoardStack stacks, List<List<Card>> lists){
        this.deck = deck;
        this.stacks = stacks;
        this.lists = lists;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public BoardStack getStacks() {
        return stacks;
    }

    public List<List<Card>> getLists() {
        return lists;
    }
}
