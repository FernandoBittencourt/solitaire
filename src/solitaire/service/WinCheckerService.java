package solitaire.service;

import solitaire.domain.Board;

public class WinCheckerService {

    public static boolean isWin(Board board){
        var stacks = board.getStacks();
        return (!stacks.getHeart().isEmpty() && stacks.getHeart().peek().getValue() == 13) &&
                (!stacks.getDiamond().isEmpty() && stacks.getDiamond().peek().getValue() == 13) &&
                (!stacks.getClub().isEmpty() && stacks.getClub().peek().getValue() == 13) &&
                (!stacks.getSpade().isEmpty() && stacks.getSpade().peek().getValue() == 13);
    }
}
