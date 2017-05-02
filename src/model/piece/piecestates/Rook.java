package model.piece.piecestates;

import model.GameState;
import model.Move;
import model.piece.Piece;

import java.util.LinkedList;
import java.util.List;

class Rook extends PieceState {
    @Override
    public List<Move> getLegalMoves(GameState gameState, Piece piece) {
        Piece[][] board = gameState.getBoard();
        List<Move> legalMoves = new LinkedList<>();
        int x0 = piece.getX();
        int y0 = piece.getY();
        for (int x = x0 + 1; x < board.length; x++) {
            legalMoves.add(new Move(piece, x, y0));
            if (board[x][y0] != null) break;
        }
        for (int x = x0 - 1; x >= 0; x--) {
            legalMoves.add(new Move(piece, x, y0));
            if (board[x][y0] != null) break;
        }
        for (int y = y0 + 1; y < board[x0].length; y++) {
            legalMoves.add(new Move(piece, x0, y));
            if (board[x0][y] != null) break;
        }
        for (int y = y0 - 1; y >= 0; y--) {
            legalMoves.add(new Move(piece, x0, y));
            if (board[x0][y] != null) break;
        }

        return legalMoves;
    }

    @Override
    public Piece.PieceType getPieceType() {
        return Piece.PieceType.ROOK;
    }
}
