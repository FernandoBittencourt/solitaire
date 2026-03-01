package solitaire.service;

import solitaire.domain.Board;
import solitaire.domain.BoardStack;
import solitaire.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class BoardService {
    private final DeckService deckService;
    public BoardService(DeckService deckService){
        this.deckService = deckService;
    }

    public Board create(){
        var deck = deckService.generate();
        var lists = createLists(deck);
        return new Board(deck, lists);
    }

    private List<List<Card>> createLists(List<Card> deck){
        var lists = new ArrayList<List<Card>>();
        for(int i=1; i<=7;i++){
            var list = new ArrayList<Card>();
            for(int j=0;j<i; j++){
                list.add(deck.remove(deck.toArray().length-1));
            }
            lists.add(list);
        }
        return lists;
    }

    public String print(Board board){
        StringBuilder s = new StringBuilder();
        if(!board.getDeck().isEmpty()) {
            s.append("DECK: ").append(board.getDeck().get(board.getDeck().size() - 1).getSymbol());
        } else {
            s.append("DECK: **");
        }
        s.append("\n");
        printStacks(s, board.getStacks());
        s.append("\n");

        return s.toString();

    }
    private void printStacks(StringBuilder s, BoardStack stacks){

        if(!stacks.getHeart().isEmpty()) {
            s.append(stacks.getHeart().peek().getSymbol()).append(" | ");
        } else {
            s.append("** | ");
        }

        if(!stacks.getDiamond().isEmpty()) {
            s.append(stacks.getDiamond().peek().getSymbol()).append(" | ");
        } else {
            s.append("** |");
        }

        if(!stacks.getClub().isEmpty()) {
            s.append(stacks.getClub().peek().getSymbol()).append(" | ");
        } else {
            s.append("** | ");
        }

        if(!stacks.getSpade().isEmpty()) {
            s.append(stacks.getSpade().peek().getSymbol());
        } else {
            s.append("**");
        }
    }
}
