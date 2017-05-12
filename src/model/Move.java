package model;

import model.piece.Piece;

public class Move {
    public Piece getPiece() {
        return piece;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    private Piece piece;
    private int newX;
    private int newY;

    public Move(Piece piece, int newX, int newY) {
        this.piece = piece;
        this.newX = newX;
        this.newY = newY;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Move)) return false;
        Move move = (Move) obj;
        return piece.equals(move.piece) && newX == move.newX && newY == move.newY;
    }

    @Override
    public int hashCode() {
        return piece.hashCode() + newX + newY;
    }

    public String toString() {
        return piece.getPieceType().name() + "(" + piece.getX() + "," + piece.getY() + ") to " + newX + ", " + newY;
    }
}
