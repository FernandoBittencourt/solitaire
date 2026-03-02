package solitaire.view;

import solitaire.domain.Board;
import solitaire.service.BoardService;
import solitaire.service.PrintService;
import solitaire.service.WinCheckerService;

import java.util.Scanner;

public class SolitaireCLI {

    private final BoardService boardService;
    private final Scanner scanner = new Scanner(System.in);

    public SolitaireCLI(BoardService boardService) {
        this.boardService = boardService;
    }

    public void start() {
        System.out.println("================================");
        System.out.println("      SOLITAIRE - JAVA CLI      ");
        System.out.println("================================");
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("Q. Quit");
        System.out.print("> ");

        String choice = scanner.nextLine().trim().toLowerCase();
        Board board;

        switch (choice) {
            case "1" -> board = boardService.create();
            case "2" -> {
                try {
                    board = boardService.load();
                    System.out.println("Game loaded successfully!");
                } catch (Exception e) {
                    System.out.println("Failed to load: No save file found. Starting new game...");
                    board = boardService.create();
                }
            }
            case "q" -> {
                System.out.println("Goodbye!");
                return;
            }
            default -> {
                System.out.println("Invalid option. Starting new game by default.");
                board = boardService.create();
            }
        }

        play(board);
    }

    private void play(Board board) {
        boolean running = true;

        while (running && !WinCheckerService.isWin(board)) {
            System.out.println("\n" + PrintService.print(board));
            System.out.println("Commands: [src tgt] | 's' save | 'n' new | 'q' quit");
            System.out.print("Move > ");

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "q" -> {
                    running = false;
                    continue;
                }
                case "s" -> {
                    try {
                        boardService.save(board);
                        System.out.println("Game saved to file!");
                    } catch (Exception e) {
                        System.out.println("Error saving game: " + e.getMessage());
                    }
                    continue;
                }
                case "n" -> {
                    System.out.print("Are you sure you want to start a new game? (y/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        board = boardService.create();
                        System.out.println("New game started!");
                    }
                    continue;
                }
            }

            handleMove(board, input);
        }

        if (WinCheckerService.isWin(board)) {
            System.out.println("\n" + PrintService.print(board));
            System.out.println("***************************");
            System.out.println("* YOU ARE THE WINNER! *");
            System.out.println("***************************");
            // Opcional: deletar o save após ganhar
            // boardService.deleteSave();
        }

        System.out.println("Thanks for playing!");
    }

    private void handleMove(Board board, String input) {
        String[] parts = input.split("\\s+");

        if (parts.length != 2) {
            System.out.println("Invalid input. Use 'source target' (e.g., 0 3) or a command.");
            return;
        }

        try {
            int source = Integer.parseInt(parts[0]);
            int target = Integer.parseInt(parts[1]);
            boardService.update(board, source, target);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter numbers for the move.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid move!");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}