package solitaire.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void whenSuitIsHeartIsRed(){
        var card = new Card(1, Suit.HEART);

        Assertions.assertTrue(card.isRed());
    }

    @Test
    void whenSuitIsSpadeIsBlack(){
        var card = new Card(1, Suit.SPADE);

        Assertions.assertFalse(card.isRed());
    }
}
