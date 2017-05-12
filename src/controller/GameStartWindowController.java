package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStartWindowController {
    public enum PlayerType {USER, COMPUTER}

    private static GameStartWindowController gameStartWindowController;
    private PlayerType whitePlayer;
    private PlayerType blackPlayer;

    public PlayerType getWhitePlayerType() {
        return whitePlayer;
    }

    public PlayerType getBlackPlayerType() {
        return blackPlayer;
    }


    @FXML
    ComboBox whitePlayerComboBox;
    @FXML
    ComboBox blackPlayerComboBox;
    @FXML
    Pane pane;

    public void initialize() {
        gameStartWindowController = this;
    }

    public static GameStartWindowController getInstance() {
        return gameStartWindowController;
    }

    @FXML
    public void onStartGameClick() {
        if (whitePlayerComboBox.getValue().equals("User")) {
            whitePlayer = PlayerType.USER;
        } else {
            whitePlayer = PlayerType.COMPUTER;
        }
        if (blackPlayerComboBox.getValue().equals("User")) {
            blackPlayer = PlayerType.USER;
        } else {
            blackPlayer = PlayerType.COMPUTER;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/mainWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Chess");
            stage.setScene(new Scene(fxmlLoader.load(), 500, 500));
            stage.show();
        } catch (IOException e) {
            System.err.println(e);
        }
        pane.getScene().getWindow().hide();
    }

}
