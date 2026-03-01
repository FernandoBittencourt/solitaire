package solitaire.fixture;

import solitaire.domain.Board;
import solitaire.domain.BoardStack;
import solitaire.domain.Card;
import solitaire.domain.Suit;

import java.util.ArrayList;
import java.util.List;

public class BoardFixture {

    public static Board createFixedBoard() {
        // Deck vazio para simplificar testes
        List<Card> deck = new ArrayList<>();

        // Criando listas (tableau) fixas
        List<List<Card>> lists = new ArrayList<>();

        // Coluna 1: 4♠
        List<Card> col1 = new ArrayList<>();
        col1.add(new Card(4, Suit.SPADE));
        lists.add(col1);

        // Coluna 2: 3♥ 5♦
        List<Card> col2 = new ArrayList<>();
        col2.add(new Card(3, Suit.HEART));
        col2.add(new Card(5, Suit.DIAMOND));
        lists.add(col2);

        // Coluna 3: 2♣ 6♠
        List<Card> col3 = new ArrayList<>();
        col3.add(new Card(2, Suit.CLUB));
        col3.add(new Card(6, Suit.SPADE));
        lists.add(col3);

        // Coluna 4: Rei ♥ (K♥)
        List<Card> col4 = new ArrayList<>();
        col4.add(new Card(13, Suit.HEART));
        lists.add(col4);

        // Coluna 5: vazia
        lists.add(new ArrayList<>());

        // Coluna 6: Ás ♣
        List<Card> col6 = new ArrayList<>();
        col6.add(new Card(1, Suit.CLUB));
        lists.add(col6);

        // Coluna 7: 7♦
        List<Card> col7 = new ArrayList<>();
        col7.add(new Card(7, Suit.DIAMOND));
        lists.add(col7);

        // Criando stacks (fundação) parcialmente preenchidos
        BoardStack stacks = new BoardStack();
        stacks.getHeart().push(new Card(1, Suit.HEART)); // Ás ♥
        stacks.getDiamond().push(new Card(1, Suit.DIAMOND)); // Ás ♦
        // Club e Spade vazios

        return new Board(deck, stacks, lists);
    }
}