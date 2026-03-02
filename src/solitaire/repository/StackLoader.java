package solitaire.repository;

import solitaire.domain.BoardStack;
import solitaire.domain.Card;
import solitaire.domain.Suit;

import java.util.List;
import java.util.Stack;

public class StackLoader {

    public static BoardStack getStack(List<String> lines) {
        var heartStack = new Stack<Card>();
        var diamondStack = new Stack<Card>();
        var clubStack = new Stack<Card>();
        var spadeStack = new Stack<Card>();

        // A linha 1 contém os topos das 4 pilhas: Copas Ouros Paus Espadas
        String[] topSymbols = lines.get(1).split(" ");

        if (!"-".equals(topSymbols[0])) {
            loadStack(heartStack, CardLoader.getCard(topSymbols[0]));
        }
        if (!"-".equals(topSymbols[1])) {
            loadStack(diamondStack, CardLoader.getCard(topSymbols[1]));
        }
        if (!"-".equals(topSymbols[2])) {
            loadStack(clubStack, CardLoader.getCard(topSymbols[2]));
        }
        if (!"-".equals(topSymbols[3])) {
            loadStack(spadeStack, CardLoader.getCard(topSymbols[3]));
        }

        var boardStack = new BoardStack();
        boardStack.setHeart(heartStack);
        boardStack.setDiamond(diamondStack);
        boardStack.setClub(clubStack);
        boardStack.setSpade(spadeStack);
        return boardStack;
    }


    private static void loadStack(Stack<Card> stack, Card topCard) {
        int maxValue = topCard.getValue();
        Suit suit = topCard.getSuit();

        // Preenche a pilha do Ás (1) até o valor do topo
        for (int v = 1; v <= maxValue; v++) {
            stack.push(new Card(v, suit));
        }
    }
}