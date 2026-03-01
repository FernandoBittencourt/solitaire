package solitaire.view;

import solitaire.domain.Board;
import solitaire.service.BoardService;
import solitaire.service.PrintService;

import java.util.Scanner;

public class SolitaireCLI {

    private final BoardService boardService;
    private final Scanner scanner = new Scanner(System.in);

    public SolitaireCLI(BoardService boardService) {
        this.boardService = boardService;
    }

    public void start() {
        Board board = boardService.create();
        boolean running = true;

        while (running && !boardService.isWin(board)) {
            System.out.println(PrintService.print(board));
            System.out.println("Enter move (source target) or 'q' to quit:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                running = false;
                continue;
            }

            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid input. Example: 0 3");
                continue;
            }

            try {
                int source = Integer.parseInt(parts[0]);
                int target = Integer.parseInt(parts[1]);
                boardService.update(board, source, target);
            } catch (NumberFormatException e) {
                System.out.println("Please enter numbers only.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid move!");
            }
        }
        if(boardService.isWin(board)){
            System.out.println("You are the Winner!");
        }
        System.out.println("Thanks for playing!");
    }

}
