import solitaire.service.BoardService;
import solitaire.service.DeckService;

public class Main {
    public static void main(String[] args) {

        System.out.println("** Solitaire **");
        var deckService = new DeckService(12L);
        var boardService = new BoardService(deckService);
        var board = boardService.create();
        System.out.println(boardService.print(board));



    }
}