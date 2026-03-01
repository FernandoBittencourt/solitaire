package solitaire.domain;

import java.util.Stack;

public class BoardStack {
    private Stack<Card> heart;
    private Stack<Card> diamond;
    private Stack<Card> spade;
    private Stack<Card> club;

    public BoardStack(){
        heart = new Stack<>();
        diamond = new Stack<>();
        spade = new Stack<>();
        club = new Stack<>();
    }

    public Stack<Card> getClub() {
        return club;
    }

    public Stack<Card> getDiamond() {
        return diamond;
    }

    public Stack<Card> getHeart() {
        return heart;
    }

    public Stack<Card> getSpade() {
        return spade;
    }

    public void setClub(Stack<Card> club) {
        this.club = club;
    }

    public void setDiamond(Stack<Card> diamond) {
        this.diamond = diamond;
    }

    public void setHeart(Stack<Card> heart) {
        this.heart = heart;
    }

    public void setSpade(Stack<Card> spade) {
        this.spade = spade;
    }
}
