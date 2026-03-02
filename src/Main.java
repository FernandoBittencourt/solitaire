import solitaire.repository.BoardRepository;
import solitaire.service.BoardBuilderService;
import solitaire.service.BoardMoverService;
import solitaire.service.BoardService;
import solitaire.service.DeckService;
import solitaire.view.SolitaireCLI;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var random = new Random();
        var fileName = "save.txt";
        var deckService = new DeckService(random);
        var boardMoverService = new BoardMoverService(deckService);
        var boardRepository = new BoardRepository(fileName);
        var boardBuilderService = new BoardBuilderService(deckService, boardRepository);
        var boardService = new BoardService(boardMoverService, boardBuilderService);
        new SolitaireCLI(boardService).start();
    }
}