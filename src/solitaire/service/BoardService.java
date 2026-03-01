package solitaire.service;

import solitaire.domain.Board;
import solitaire.domain.Card;
import solitaire.domain.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                list.add(deck.remove(deck.size()-1));
            }
            lists.add(list);
        }
        return lists;
    }

    /**
     * Move a card on the board from a source position to a target position.
     * <p>
     * The source and target positions are specified by integer indices and can represent:
     * <ul>
     *     <li>The main deck: index 0</li>
     *     <li>Tableau lists (columns): indices 1 to 7</li>
     *     <li>Stacks (foundations): indices 8 to 11</li>
     * </ul>
     * This method updates the board in place and does not return a new instance.
     * </p>
     *
     * @param board  the board containing the deck, stacks, and lists to be updated
     * @param source the index of the source position from which the card will be moved
     * @param target the index of the target position where the card will be placed
     */
    public void update(Board board, int source, int target){
        if(target==0 && source==0){
            deckService.nextCard(board.getDeck());
        } else {
            if (source < 0 || source > 11) {
                throw new IllegalArgumentException();
            }
            if (target < 1 || target > 11) {
                throw new IllegalArgumentException();
            }
            if (!validateMove(board, source, target)) {
                throw new IllegalArgumentException();
            }

            move(board, source, target);
        }
    }

    private void move(Board board, int source, int target) {
        var card = removeCardFromBoard(board,source);
        addCardOnBoard(board, card.get(), target);
    }

    private void addCardOnBoard(Board board, Card card, int position) {
        if(position>=1 && position<=7){
            board.getLists().get(position-1).add(card);
        } else {
            var stacks = board.getStacks();
            if(position==8){
                stacks.getHeart().push(card);
            } else if(position==9){
                stacks.getDiamond().push(card);
            } else if(position==10){
                stacks.getClub().push(card);
            } else if(position==11) {
                stacks.getSpade().push(card);
            }
        }
    }

    private boolean validateMove(Board board, int source, int target) {
        var cardSource = getFromBoard(board, source);
        if(cardSource.isEmpty()){
            return false;
        }
        var cardTarget = getFromBoard(board, target);
        if(target<8){
            if(cardSource.get().getValue()==13 && cardTarget.isEmpty()){
                return true;
            }
            if(cardSource.get().getValue()!=cardTarget.get().getValue()-1){
                return false;
            }
            return cardSource.get().isRed()!= cardTarget.get().isRed();
        }
        if (target <= 11) {
            return cardTarget.map(card -> cardSource.get().getValue() == card.getValue() + 1
                    && cardSource.get().getSuit() == card.getSuit())
                    .orElseGet(() -> cardSource.get().getValue() == 1
                    && matchesStackSuit(cardSource.get(), target));
        }
        return false;
    }

    private boolean matchesStackSuit(Card card, int target) {
        return (target == 8 && card.getSuit() == Suit.HEART)
                || (target == 9 && card.getSuit() == Suit.DIAMOND)
                || (target == 10 && card.getSuit() == Suit.CLUB)
                || (target == 11 && card.getSuit() == Suit.SPADE);
    }
    private Optional<Card> getFromBoard(Board board, int position){
        if(position==0 && !board.getDeck().isEmpty()){
            return Optional.of(board.getDeck().get(board.getDeck().size()-1));
        } else if(position>=1 && position<=7){
            var list = board.getLists().get(position-1);
            if(!list.isEmpty()){
                return Optional.of(list.get(list.size()-1));
            }
        } else {
            var stacks = board.getStacks();
            if(position==8 && !stacks.getHeart().isEmpty()){
                return Optional.of(stacks.getHeart().peek());
            } else if(position==9 && !stacks.getDiamond().isEmpty()){
                return Optional.of(stacks.getDiamond().peek());
            } else if(position==10 && !stacks.getClub().isEmpty()){
                return Optional.of(stacks.getClub().peek());
            } else if(position==11 && !stacks.getSpade().isEmpty()){
                return Optional.of(stacks.getSpade().peek());
            }
        }
        return Optional.empty();
    }

    private Optional<Card> removeCardFromBoard(Board board, int position) {
        if(position==0){
            return Optional.of(board.getDeck().remove(board.getDeck().size()-1));
        } else if(position>=1 && position<=7){
            var list = board.getLists().get(position-1);
            if(!list.isEmpty()){
                return Optional.of(list.remove(list.size()-1));
            }
        } else {
            var stacks = board.getStacks();
            if(position==8 && !stacks.getHeart().isEmpty()){
                return Optional.of(stacks.getHeart().pop());
            } else if(position==9 && !stacks.getDiamond().isEmpty()){
                return Optional.of(stacks.getDiamond().pop());
            } else if(position==10 && !stacks.getClub().isEmpty()){
                return Optional.of(stacks.getClub().pop());
            } else if(position==11 && !stacks.getSpade().isEmpty()){
                return Optional.of(stacks.getSpade().pop());
            }
        }
        return Optional.empty();
    }

    public boolean isWin(Board board){
        var stacks = board.getStacks();
        return (!stacks.getHeart().isEmpty() && stacks.getHeart().peek().getValue() == 13) &&
                (!stacks.getDiamond().isEmpty() && stacks.getDiamond().peek().getValue() == 13) &&
                (!stacks.getClub().isEmpty() && stacks.getClub().peek().getValue() == 13) &&
                (!stacks.getSpade().isEmpty() && stacks.getSpade().peek().getValue() == 13);
    }
}
