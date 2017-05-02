package model.piece.piecestates;

import model.GameState;
import model.Move;
import model.piece.Piece;

import java.util.LinkedList;
import java.util.List;

class Pawn extends PieceState {
    @Override
    public List<Move> getLegalMoves(GameState gameState, Piece piece) {
        Piece[][] board = gameState.getBoard();
        List<Move> legalMoves = new LinkedList<>();
        int yDir = piece.getColor() == Piece.Color.WHITE ? 1 : -1;
        int x = piece.getX();
        int y = piece.getY();
        if (y + yDir >= 0 && y + yDir < board[x].length) {
            if (board[x][y + yDir] == null) {
                legalMoves.add(new Move(piece, x, y + yDir));
            }
            if (x - 1 >= 0 && board[x - 1][y + yDir] != null) {
                legalMoves.add(new Move(piece, x - 1, y + yDir));
            }
            if (x + 1 < board.length && board[x + 1][y + yDir] != null) {
                legalMoves.add(new Move(piece, x + 1, y + yDir));
            }
        }
        return legalMoves;
    }

    @Override
    public Piece.PieceType getPieceType() {
        return Piece.PieceType.PAWN;
    }
}
