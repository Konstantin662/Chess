package controller;

import controller.players.Computer;
import controller.players.User;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.GameState;
import model.piece.Piece;
import controller.players.Player;
import view.Square;

public class MainWindowController {
    GameState currentGameState = GameState.getInitialChessGameState();
    Player whitePlayer;
    Player blackPlayer;

    @FXML
    Pane board;
    @FXML
    Pane bgPane;
    private static final double squareWidth = 50, squareHeight = 50;
    private static MainWindowController mainWindowController;
    private Square[][] squares = new Square[8][8];

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void initialize() {
        mainWindowController = this;
        if (GameStartWindowController.getInstance().getWhitePlayerType() == GameStartWindowController.PlayerType.USER) {
            whitePlayer = new User();
        } else {
            whitePlayer = new Computer();
        }
        if (GameStartWindowController.getInstance().getBlackPlayerType() == GameStartWindowController.PlayerType.USER) {
            blackPlayer = new User();
        } else {
            blackPlayer = new Computer();
        }
        drawBoard();
        onMove();
    }

    private void drawBoard() {
        board.getChildren().clear();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square square = new Square(x, y, squareWidth, squareHeight);
                board.getChildren().add(square);
                squares[x][y] = square;
            }
        }
        updateBoardState();
    }

    private void updateBoardState() {
        for (Node node : board.getChildren()) {
            if (node instanceof Square) {
                Square square = (Square) node;
                square.setPiece(currentGameState.getBoard()[square.getX()][square.getY()]);
            }
        }
        if (currentGameState.getWinner() != null) {
            bgPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (currentGameState.getCurrentPlayerColor() == Piece.Color.WHITE) {
            bgPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            bgPane.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public static MainWindowController getInstance() {
        return mainWindowController;
    }

    public void onSquareClick(int x, int y) {
        if (currentGameState.getCurrentPlayerColor() == Piece.Color.WHITE) {
            whitePlayer.onSquareClick(x, y);
        } else {
            blackPlayer.onSquareClick(x, y);
        }
    }

    public void onMove() {
        updateBoardState();
        if (currentGameState.getWinner() == null) {
            if (currentGameState.getCurrentPlayerColor() == Piece.Color.WHITE) {
                whitePlayer.onMove();
            } else {
                blackPlayer.onMove();
            }
        }
    }
}
