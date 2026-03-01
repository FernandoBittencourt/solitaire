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


}
