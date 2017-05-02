package model;

import model.piece.Piece;
import java.util.LinkedList;
import java.util.List;

public class GameState {
    public Piece[][] getBoard() {
        return board;
    }

    private Piece[][] board = new Piece[8][8];
    private List<Piece> pieces = new LinkedList<>();

    Piece.Color currentPlayerColor;

    public List<Move> getLegalMoves() {
        List<Move> legalMoves = new LinkedList<>();
        for (Piece piece : pieces) {
            if(piece.getColor()==currentPlayerColor)
            legalMoves.addAll(piece.getLegalMoves(this));
        }
        return legalMoves;
    }

    public void makeAMove(Move move) {
        //TODO: complete the makeAMove method
    }
}
