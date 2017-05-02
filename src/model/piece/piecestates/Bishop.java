package model.piece.piecestates;

import model.GameState;
import model.Move;
import model.piece.Piece;

import java.util.LinkedList;
import java.util.List;

class Bishop extends PieceState {
    @Override
    public List<Move> getLegalMoves(GameState gameState, Piece piece) {
        Piece[][] board = gameState.getBoard();
        List<Move> legalMoves = new LinkedList<>();
        int x0 = piece.getX();
        int y0 = piece.getY();
        for (int x = x0 + 1, y = y0 + 1; x < board.length && y < board[x].length; x++, y++) {
            legalMoves.add(new Move(piece, x, y));
            if (board[x][y] != null) break;
        }
        for (int x = x0 + 1, y = y0 - 1; x < board.length && y >= 0; x++, y--) {
            legalMoves.add(new Move(piece, x, y));
            if (board[x][y] != null) break;
        }
        for (int x = x0 - 1, y = y0 + 1; x >= 0 && y < board[x].length; x--, y++) {
            legalMoves.add(new Move(piece, x, y));
            if (board[x][y] != null) break;
        }
        for (int x = x0 - 1, y = y0 - 1; x >= 0 && y >= 0; x--, y--) {
            legalMoves.add(new Move(piece, x, y));
            if (board[x][y] != null) break;
        }
        return legalMoves;
    }

    @Override
    public Piece.PieceType getPieceType() {
        return Piece.PieceType.BISHOP;
    }
}
