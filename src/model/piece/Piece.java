package model.piece;

import model.GameState;
import model.Move;
import model.piece.piecestates.PieceState;

import java.util.List;

public class Piece {
    public enum Color{WHITE,BLACK}
    public enum PieceType{PAWN,KNIGHT,BISHOP,ROOK,QUEEN,KING}
    private PieceState pieceState;
    private Color color;
    public Color getColor(){
        return color;
    }

    private int x,y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece(PieceType pieceType, Color color, int x, int y){
        this.pieceState = PieceState.getPieceState(pieceType);
        this.color=color;
        this.x=x;
        this.y=y;
    }

    public List<Move> getLegalMoves(GameState gameState){
        return pieceState.getLegalMoves(gameState, this);
    }

}
