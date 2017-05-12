package controller.players;

import controller.MainWindowController;
import model.GameState;
import model.Move;
import model.piece.Piece;
import view.Square;

import java.util.LinkedList;
import java.util.List;

public class User implements Player {
    private Piece selectedPiece;
    private List<Square> selectedSquares = new LinkedList<>();

    @Override
    public void onSquareClick(int x, int y) {
        GameState currentGameState = MainWindowController.getInstance().getCurrentGameState();
        Piece[][] board = currentGameState.getBoard();
        if (selectedPiece == null) {
            if (board[x][y] != null && board[x][y].getColor() == currentGameState.getCurrentPlayerColor()) {
                selectedPiece = board[x][y];
                for (Move move : board[x][y].getLegalMoves(currentGameState)) {
                    Square square = MainWindowController.getInstance().getSquares()[move.getNewX()][move.getNewY()];
                    square.select();
                    selectedSquares.add(square);
                }
            }
        } else {
            for (Square square : selectedSquares) {
                square.deselect();
            }
            selectedSquares.clear();
            Move move = new Move(selectedPiece, x, y);
            if (selectedPiece.getLegalMoves(currentGameState).contains(move)) {
                currentGameState.makeAMove(move);
                selectedPiece = null;
                MainWindowController.getInstance().onMove();

            } else {
                selectedPiece = null;
                onSquareClick(x, y);
            }
        }
    }

    @Override
    public void onMove() {
    }
}
