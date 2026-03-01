import solitaire.service.BoardService;
import solitaire.service.DeckService;
import solitaire.service.PrintService;

public class Main {
    public static void main(String[] args) {

        System.out.println("** Solitaire **");
        var deckService = new DeckService(11L);
        var boardService = new BoardService(deckService);
        var board = boardService.create();
        System.out.println(PrintService.print(board));



    }
}