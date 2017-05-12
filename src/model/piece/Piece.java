package model.piece;

import model.GameState;
import model.Move;
import model.piece.piecestates.PieceState;

import java.util.Collections;
import java.util.List;

public class Piece {
    public enum Color {WHITE, BLACK}

    public enum PieceType {PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING}

    private PieceState pieceState;
    private Color color;
    private boolean hasMoved = false;
    private int x, y;

    public Piece clone() {
        int cloneX = x, cloneY = y;
        boolean cloneHasMoved = hasMoved ? true : false;
        Piece clone = new Piece(getPieceType(), color, cloneX, cloneY);
        clone.hasMoved = cloneHasMoved;
        return clone;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public Color getColor() {
        return color;
    }


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
        if (gameState.getWinner() != null) return Collections.emptyList();
        return pieceState.getLegalMoves(gameState, this);
    }

    public PieceType getPieceType() {
        return pieceState.getPieceType();
    }

    public void moveTo(int x, int y) {
        hasMoved = true;
        this.x = x;
        this.y = y;
        if (pieceState.getPieceType() == PieceType.PAWN && ((color == Color.BLACK && y == 0) || (color == Color.WHITE && y == 7))) {
            pieceState = PieceState.queen;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Piece)) return false;
        Piece piece = (Piece) obj;
        return piece.hasMoved == hasMoved && piece.pieceState.equals(pieceState) && piece.x == x && piece.y == y && color == color;
    }
}
