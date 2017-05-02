package model.piece.piecestates;

import model.GameState;
import model.Move;
import model.piece.Piece;

import java.util.LinkedList;
import java.util.List;

class Knight extends PieceState {
    @Override
    public List<Move> getLegalMoves(GameState gameState, Piece piece) {

        return null;
    }

    @Override
    public Piece.PieceType getPieceType() {
        return Piece.PieceType.KNIGHT;
    }
}
