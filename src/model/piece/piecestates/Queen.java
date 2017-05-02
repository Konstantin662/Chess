package model.piece.piecestates;

import model.GameState;
import model.Move;
import model.piece.Piece;

import java.util.LinkedList;
import java.util.List;

class Queen extends PieceState {
    @Override
    public List<Move> getLegalMoves(GameState gameState, Piece piece) {
        List<Move> legalMoves = new LinkedList<>();
        //since queen moves as bishop and rook combined:
        legalMoves.addAll(PieceState.bishop.getLegalMoves(gameState, piece));
        legalMoves.addAll(PieceState.rook.getLegalMoves(gameState, piece));
        return legalMoves;
    }

    @Override
    public Piece.PieceType getPieceType() {
        return Piece.PieceType.QUEEN;
    }
}
