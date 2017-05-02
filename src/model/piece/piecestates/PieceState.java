package model.piece.piecestates;

import model.GameState;
import model.Move;
import model.piece.Piece;

import java.util.List;

public abstract class PieceState {
    public abstract List<Move> getLegalMoves(GameState gameState, Piece piece);

    public abstract Piece.PieceType getPieceType();

    public static final PieceState pawn = new Pawn();
    public static final PieceState knight = new Knight();
    public static final PieceState queen = new Queen();
    public static final PieceState bishop = new Bishop();
    public static final PieceState rook = new Rook();
    public static final PieceState king = new King();


    public static PieceState getPieceState(Piece.PieceType pieceType) {
        switch (pieceType) {
            case KNIGHT:
                return knight;
            case PAWN:
                return pawn;
            default:
                return null;
        }
    }


}
