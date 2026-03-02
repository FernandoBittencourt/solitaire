package solitaire.domain;

public class Card {
    private final int value;
    private final Suit suit;
    private boolean isReveled;

    public Card(int value, Suit suit){
        if(suit==null){
            throw new IllegalArgumentException();
        }
        if(value<1 || value>13){
            throw new IllegalArgumentException();
        }
        this.value=value;
        this.suit=suit;
        this.isReveled=true;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit(){
        return suit;
    }

    public boolean getIsReveled(){
        return isReveled;
    }

    public void setIsReveled(boolean isReveled){
        this.isReveled=isReveled;
    }

    public boolean isRed(){
        return Suit.HEART.equals(suit) || Suit.DIAMOND.equals(suit);
    }


    public String getSymbol(){
        return getSymbolNumber() + getSymbolSuit();
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