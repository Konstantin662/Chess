package model.piece;

import model.GameState;
import model.Move;
import model.piece.piecestates.PieceState;

import java.util.List;

public class Piece {
    public enum Color {WHITE, BLACK}

    public enum PieceType {PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING}

    private PieceState pieceState;
    private Color color;
    private boolean hasMoved = false;

    public boolean hasMoved() {
        return hasMoved;
    }

    public Color getColor() {
        return color;
    }

    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece(PieceType pieceType, Color color, int x, int y) {
        this.pieceState = PieceState.getPieceState(pieceType);
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public List<Move> getLegalMoves(GameState gameState) {
        return pieceState.getLegalMoves(gameState, this);
    }

    public PieceType getPieceType() {
        return pieceState.getPieceType();
    }

    public void moveTo(int x, int y) {
        hasMoved = true;
        this.x = x;
        this.y = y;
        if (pieceState.getPieceType() == PieceType.PAWN && (color == Color.BLACK && y == 0) || (color == Color.WHITE && y == 7)) {
            pieceState = PieceState.queen;
        }
    }
}
