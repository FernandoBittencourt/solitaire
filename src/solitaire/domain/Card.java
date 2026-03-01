package solitaire.domain;

public class Card {
    private final int value;
    private final Suit suit;

    public Card(int value, Suit suit){
        if(suit==null){
            throw new IllegalArgumentException();
        }
        if(value<1 || value>13){
            throw new IllegalArgumentException();
        }
        this.value=value;
        this.suit=suit;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit(){
        return suit;
    }

    public boolean isRed(){
        if(Suit.HEART.equals(suit) || Suit.DIAMOND.equals(suit)){
            return true;
        }
        return false;
    }


    public String getSymbol(){
        return getSymbolNumber()+getSymbolSuit();
    }
    private String getSymbolNumber(){
        if(value == 1){
            return "A";
        } else if(value == 11){
            return "J";
        } else if(value == 12){
            return "Q";
        } else if(value == 13){
            return "K";
        }
        return value + "";
    }
    private String getSymbolSuit(){
        if(Suit.HEART.equals(suit)){
            return "♥";
        } else if(Suit.DIAMOND.equals(suit)){
            return "♦";
        } else if(Suit.CLUB.equals(suit)){
            return "♣";
        } else{
            return "♠";
        }
    }

}