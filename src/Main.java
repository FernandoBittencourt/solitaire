import solitaire.service.BoardBuilderService;
import solitaire.service.BoardMoverService;
import solitaire.service.BoardService;
import solitaire.service.DeckService;
import solitaire.view.SolitaireCLI;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("** Solitaire **");
        var random = new Random();
        var deckService = new DeckService(random);
        var boardMoverService = new BoardMoverService(deckService);
        var boardBuilderService = new BoardBuilderService(deckService);
        var boardService = new BoardService(boardMoverService, boardBuilderService);
        new SolitaireCLI(boardService).start();
    }
}