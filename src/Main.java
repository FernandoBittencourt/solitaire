import solitaire.service.BoardService;
import solitaire.service.DeckService;
import solitaire.view.SolitaireCLI;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("** Solitaire **");
        var random=new Random();
        DeckService deckService = new DeckService(random);
        BoardService boardService = new BoardService(deckService);
        new SolitaireCLI(boardService).start();
    }
}