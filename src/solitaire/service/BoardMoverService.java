package solitaire.service;

import solitaire.domain.Board;
import solitaire.domain.Card;
import solitaire.domain.Suit;

import java.util.Optional;

public class BoardMoverService {

    private final DeckService deckService;

    public BoardMoverService(DeckService deckService){
        this.deckService = deckService;
    }
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
                var removedCard = list.remove(list.size() - 1);
                if(!list.isEmpty()){
                    list.get(list.size() - 1).setIsReveled(true);
                }
                return Optional.of(removedCard);
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

}
