package controller.players;

import model.GameState;

public interface Player {
    void makeNextMove(GameState gameState);
}
