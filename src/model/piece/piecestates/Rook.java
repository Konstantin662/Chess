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
        for (int dir = +1; dir >= -1; dir -= 2) {
            for (int x = x0 + dir; x >= 0 && x < board.length; x += dir) {
                if (board[x][y0] == null) {
                    legalMoves.add(new Move(piece, x, y0));
                } else {
                    if (board[x][y0].getColor() != piece.getColor()) {
                        legalMoves.add(new Move(piece, x, y0));
                    }
                    break;
                }
            }
            for (int y = y0 + dir; y >= 0 && y < board[x0].length; y += dir) {
                if (board[x0][y] == null) {
                    legalMoves.add(new Move(piece, x0, y));
                } else {
                    if (board[x0][y].getColor() != piece.getColor()) {
                        legalMoves.add(new Move(piece, x0, y));
                    }
                    break;
                }
            }
        }
        return legalMoves;
    }

    @Override
    public Piece.PieceType getPieceType() {
        return Piece.PieceType.ROOK;
    }
}
