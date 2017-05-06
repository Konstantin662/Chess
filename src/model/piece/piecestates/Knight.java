package model.piece.piecestates;

import model.GameState;
import model.Move;
import model.piece.Piece;

import java.util.LinkedList;
import java.util.List;

class Knight extends PieceState {
    @Override
    public List<Move> getLegalMoves(GameState gameState, Piece piece) {
        List<Move> legalMoves = new LinkedList<>();
        Piece[][] board = gameState.getBoard();
        int[] dXs = {-1, -2, -2, -2, 1, 2, 2, 1};
        int[] dYs = {2, 1, -1, -2, -2, -1, 1, 2};
        int x, y;
        for (int i = 0; i < dXs.length; i++) {
            x = piece.getX() + dXs[i];
            y = piece.getY() + dYs[i];
            if (x >= 0 && y >= 0 && x < board.length && y < board[x].length
                    && (board[x][y] == null || board[x][y].getColor() != piece.getColor())) {
                legalMoves.add(new Move(piece, x, y));
            }
        }
        return legalMoves;
    }

    @Override
    public Piece.PieceType getPieceType() {
        return Piece.PieceType.KNIGHT;
    }
}
