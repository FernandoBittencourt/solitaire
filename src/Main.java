import solitaire.domain.Card;
import solitaire.domain.Suit;
import solitaire.service.DeckService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        var deckService = new DeckService(12L);
        var cardArray = deckService.generate();

        for(var card: cardArray){
            System.out.println(card.getSymbol());
        }
    }
}