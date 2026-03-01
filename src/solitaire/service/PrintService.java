package solitaire.service;

import solitaire.domain.Board;
import solitaire.domain.BoardStack;
import solitaire.domain.Card;

import java.util.List;

public class PrintService {
    public static String print(Board board){
        StringBuilder s = new StringBuilder();
        if(!board.getDeck().isEmpty()) {
            s.append("DECK: ").append(board.getDeck().get(board.getDeck().size() - 1).getSymbol());
        } else {
            s.append("DECK: **");
        }
        s.append("\n");
        printStacks(s, board.getStacks());
        s.append("\n");

        printLists(s, board.getLists());
        s.append("\n");
        return s.toString();

    }

    private static void printLists(StringBuilder s, List<List<Card>> lists) {
        for(var list: lists){
            for (Card card : list) {
                s.append(card.getSymbol()).append("  ");
            }
            s.append("\n");
        }
    }

    private static void printStacks(StringBuilder s, BoardStack stacks){

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
