package model.piece.piecestates;

import model.GameState;
import model.Move;
import model.piece.Piece;

import java.util.LinkedList;
import java.util.List;

class King extends PieceState {
    @Override
    public List<Move> getLegalMoves(GameState gameState, Piece piece) {
        Piece[][] board = gameState.getBoard();
        List<Move> legalMoves = new LinkedList<>();
        int x0 = piece.getX();
        int y0 = piece.getY();
        int x,y;
        for(int dx=-1;dx<=1;dx++){
            for(int dy=-1;dy<=1;dy++){
                x=x0+dx;
                y=y0+dy;
                if(x>=0&&y>=0&&x<board.length&&y<board[x].length&&(dy!=0||dx!=0)){
                    legalMoves.add(new Move(piece,x,y));
                }
            }
        }
        return legalMoves;
    }

    @Override
    public Piece.PieceType getPieceType() {
        return Piece.PieceType.KING;
    }
}
