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
        for (int dx = +1; dx >= -1; dx -= 2) {
            for (int dy = +1; dy >= -1; dy -= 2) {
                for (int x = x0 + dx, y = y0 + dy; x >= 0 && y >= 0 && x < board.length && y < board[x].length; x += dx, y += dy) {
                    if (board[x][y] == null) {
                        legalMoves.add(new Move(piece, x, y));
                    } else {
                        if (board[x][y].getColor() != piece.getColor()) {
                            legalMoves.add(new Move(piece, x, y));
                        }
                        break;
                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public Piece.PieceType getPieceType() {
        return Piece.PieceType.BISHOP;
    }
}
