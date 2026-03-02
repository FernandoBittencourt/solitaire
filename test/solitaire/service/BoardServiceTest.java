package solitaire.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import solitaire.fixture.BoardFixture;
import solitaire.repository.BoardRepository;

import java.util.Random;

public class BoardServiceTest {


    @Test
    void validate(){
        var board = BoardFixture.createFixedBoard();
        var random=new Random();
        var deckService = new DeckService(random);
        var boardMoverService = new BoardMoverService(deckService);
        var boardRepository = new BoardRepository("test.txt");
        var boardBuilderService = new BoardBuilderService(deckService, boardRepository);
        var boardService = new BoardService(boardMoverService, boardBuilderService);

        boardService.update(board, 6, 10);

        Assertions.assertEquals(1, board.getStacks().getClub().size());
        Assertions.assertEquals("A♣", board.getStacks().getClub().peek().getSymbol());
    }


    @Test
    void validate2(){
        var board = BoardFixture.createFixedBoard();
        var random=new Random();
        var deckService = new DeckService(random);
        var boardMoverService = new BoardMoverService(deckService);
        var boardRepository = new BoardRepository("test.txt");
        var boardBuilderService = new BoardBuilderService(deckService, boardRepository);
        var boardService = new BoardService(boardMoverService, boardBuilderService);

        boardService.update(board, 1, 2);

        Assertions.assertEquals(3, board.getLists().get(1).size());
    }
}
