package solitaire.repository;

import solitaire.domain.Board;
import solitaire.domain.BoardStack;
import solitaire.domain.Card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardRepository {
    private final String fileName;

    public BoardRepository(String fileName){
        this.fileName=fileName;
    }

    public void save(Board board) {
        var path = Path.of(fileName);
        var text = new ArrayList<String>();
        var deck = "-";
        if (!board.getDeck().isEmpty()) {
            deck = board.getDeck().stream().map(Card::getSymbol).collect(Collectors.joining(" "));
        }
        text.add(deck);
        var stacks = Stream.of(
                        board.getStacks().getHeart(),
                        board.getStacks().getDiamond(),
                        board.getStacks().getClub(),
                        board.getStacks().getSpade()
                )
                .map(stack -> !stack.isEmpty() ? stack.peek().getSymbol() : "-")
                .collect(Collectors.joining(" "));
        text.add(stacks);
        for(var list: board.getLists()){
            var column = list.isEmpty()? "-" :
                    list.stream().map(Card::getSymbol).collect(Collectors.joining(" "));
            text.add(column);
        }

        try {
            Files.write(path, text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Board load(){
        try {
            var lines = Files.readAllLines(Path.of(fileName));
            List<Card> deck = DeckLoader.getDeck(lines);
            BoardStack stacks = StackLoader.getStack(lines);
            List<List<Card>> lists = ColumnLoader.getColumns(lines);
            return new Board(deck, stacks, lists);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
