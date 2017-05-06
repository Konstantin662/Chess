package controller;

import model.GameState;
import model.piece.Piece;
import controller.players.Player;

public class Controller {
    GameState currentGameState = GameState.getInitialChessGameState();
    Player whitePlayer;
    Player blackPlayer;

    public void startGame() {
        while (currentGameState.getWinner() == null) {
            if (currentGameState.getCurrentPlayerColor() == Piece.Color.WHITE) {
                whitePlayer.makeNextMove(currentGameState);
            } else {
                blackPlayer.makeNextMove(currentGameState);
            }
        }
    }
}
