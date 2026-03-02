package solitaire.service;

import solitaire.domain.Board;
import solitaire.domain.Card;
import solitaire.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilderService {
    private final DeckService deckService;
    private final BoardRepository repository;
    public BoardBuilderService(DeckService deckService, BoardRepository repository){
        this.deckService = deckService;
        this.repository = repository;
    }

    public Board create(){
        var deck = deckService.generate();
        var lists = createLists(deck);
        return new Board(deck, lists);
    }

    public Board load(){
        return repository.load();
    }

    private List<List<Card>> createLists(List<Card> deck){
        var lists = new ArrayList<List<Card>>();
        for(int i=1; i<=7;i++){
            var list = new ArrayList<Card>();
            for(int j=0;j<i; j++){
                var card = deck.remove(deck.size()-1);
                if(j!=i-1){
                    card.setIsReveled(false);
                }
                list.add(card);
            }
            lists.add(list);
        }
        return lists;
    }

    public void save(Board board) {
        repository.save(board);
    }
}
