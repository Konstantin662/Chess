package model;

import model.piece.Piece;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameState {
    public Piece[][] getBoard() {
        return board;
    }

    private Piece[][] board = new Piece[8][8];
    private List<Piece> pieces = new LinkedList<>();

    Piece.Color currentPlayerColor = Piece.Color.WHITE;
    private Piece.Color lastCalculatedMovesColor = null;
    private List<Move> lastCalculatedMoves;
    private Piece.Color winner = null;

    public Piece.Color getWinner() {
        return winner;
    }

    public List<Move> getLegalMoves() {
        if (winner != null) {
            return Collections.emptyList();
        }
        if (currentPlayerColor == lastCalculatedMovesColor) {
            return lastCalculatedMoves;
        } //else
        List<Move> legalMoves = new LinkedList<>();
        for (Piece piece : pieces) {
            if (piece.getColor() == currentPlayerColor)
                legalMoves.addAll(piece.getLegalMoves(this));
        }
        lastCalculatedMoves = legalMoves;
        lastCalculatedMovesColor = currentPlayerColor;
        return legalMoves;
    }

    public Piece.Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    private GameState() {
    }

    public void makeAMove(Move move) {
        if (!getLegalMoves().contains(move)) {
            throw new RuntimeException("Illegal move");
        }
        int newX = move.getNewX(), newY = move.getNewY(),
                x = move.getPiece().getX(), y = move.getPiece().getY();
        board[x][y] = null;
        if (board[newX][newY] != null) {
            if (board[newX][newY].getPieceType() == Piece.PieceType.KING) {
                winner = currentPlayerColor;
            }
            pieces.remove(board[newX][newY]);
        }
        board[newX][newY] = move.getPiece();
        move.getPiece().moveTo(newX, newY);
        currentPlayerColor = (currentPlayerColor == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    }

    public static GameState getInitialChessGameState() {
        GameState initialChessGameState = new GameState();
        //Pawns
        Piece newPawn;
        for (int x = 0; x < initialChessGameState.board.length; x++) {
            newPawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, x, 1);
            initialChessGameState.board[x][1] = newPawn;
            initialChessGameState.pieces.add(newPawn);
            newPawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, x, 6);
            initialChessGameState.board[x][6] = newPawn;
            initialChessGameState.pieces.add(newPawn);
        }
        //Rooks
        initialChessGameState.board[0][0] = new Piece(Piece.PieceType.ROOK, Piece.Color.WHITE, 0, 0);
        initialChessGameState.board[7][0] = new Piece(Piece.PieceType.ROOK, Piece.Color.WHITE, 7, 0);
        initialChessGameState.board[7][7] = new Piece(Piece.PieceType.ROOK, Piece.Color.BLACK, 7, 7);
        initialChessGameState.board[0][7] = new Piece(Piece.PieceType.ROOK, Piece.Color.BLACK, 0, 7);
        //Knights
        initialChessGameState.board[1][0] = new Piece(Piece.PieceType.KNIGHT, Piece.Color.WHITE, 1, 0);
        initialChessGameState.board[6][0] = new Piece(Piece.PieceType.KNIGHT, Piece.Color.WHITE, 6, 0);
        initialChessGameState.board[1][7] = new Piece(Piece.PieceType.KNIGHT, Piece.Color.BLACK, 1, 7);
        initialChessGameState.board[6][7] = new Piece(Piece.PieceType.KNIGHT, Piece.Color.WHITE, 6, 7);
        //Bishops
        initialChessGameState.board[2][0] = new Piece(Piece.PieceType.BISHOP, Piece.Color.WHITE, 2, 0);
        initialChessGameState.board[5][0] = new Piece(Piece.PieceType.BISHOP, Piece.Color.WHITE, 5, 0);
        initialChessGameState.board[2][7] = new Piece(Piece.PieceType.BISHOP, Piece.Color.BLACK, 2, 7);
        initialChessGameState.board[5][7] = new Piece(Piece.PieceType.BISHOP, Piece.Color.WHITE, 5, 7);
        //Kings
        initialChessGameState.board[4][0] = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, 4, 0);
        initialChessGameState.board[4][7] = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, 4, 7);
        //Queens
        initialChessGameState.board[3][0] = new Piece(Piece.PieceType.QUEEN, Piece.Color.WHITE, 3, 0);
        initialChessGameState.board[3][7] = new Piece(Piece.PieceType.QUEEN, Piece.Color.WHITE, 3, 7);
        return initialChessGameState;
    }

}
