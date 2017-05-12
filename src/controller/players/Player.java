package controller.players;

import model.GameState;

public interface Player {
    void onSquareClick(int x, int y);

    void onMove();
}
