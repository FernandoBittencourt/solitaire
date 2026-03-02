package solitaire.repository;

import solitaire.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class ColumnLoader {

    public static List<List<Card>> getColumns(List<String> lines) {
        var lists = new ArrayList<List<Card>>();

        // Começa na linha 2, porque 0 = deck, 1 = stacks
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            var column = new ArrayList<Card>();
            if (!line.equals("-")) {
                for (String symbol : line.split(" ")) {
                    column.add(CardLoader.getCard(symbol));
                }
            }
            lists.add(column);
        }

        return lists;
    }
}