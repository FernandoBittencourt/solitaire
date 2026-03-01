package solitaire.service;

import solitaire.domain.Board;

public class BoardService {
    private final BoardBuilderService boardBuilderService;
    private final BoardMoverService boardMoverService;

    public BoardService(BoardMoverService boardMoverService, BoardBuilderService boardBuilderService) {
        this.boardMoverService = boardMoverService;
        this.boardBuilderService = boardBuilderService;
    }

    public Board create() {
        return boardBuilderService.create();
    }


    /**
     * Move a card on the board from a source position to a target position.
     * <p>
     * The source and target positions are specified by integer indices and can represent:
     * <ul>
     *     <li>The main deck: index 0</li>
     *     <li>Tableau lists (columns): indices 1 to 7</li>
     *     <li>Stacks (foundations): indices 8 to 11</li>
     * </ul>
     * This method updates the board in place and does not return a new instance.
     * </p>
     *
     * @param board  the board containing the deck, stacks, and lists to be updated
     * @param source the index of the source position from which the card will be moved
     * @param target the index of the target position where the card will be placed
     */
    public void update(Board board, int source, int target) {
        boardMoverService.update(board, source, target);
    }

}