package view;

import controller.MainWindowController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.piece.Piece;

public class Square extends StackPane {
    private int x;
    private int y;
    private Piece piece;
    private ImageView imageView = new ImageView();
    private Rectangle selection;
    private Rectangle bg;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public Square(int x, int y, double width, double height) {
        this.x = x;
        this.y = y;
        bg = new Rectangle(0, 0, width, height);

        if ((x + y) % 2 == 0) {
            bg.setFill(Color.GRAY);
        } else {
            bg.setFill(Color.WHITE);
        }
        this.getChildren().add(bg);
        selection = new Rectangle(0,0,width,height);
        selection.setFill(Color.rgb(135,206,250,0.5));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        this.getChildren().add(imageView);
        setPrefHeight(height);
        setPrefWidth(width);
        setLayoutX(x * width);
        setLayoutY((7 - y) * height);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainWindowController.getInstance().onSquareClick(Square.this.x, Square.this.y);
            }
        });

    }

    public void setPiece(Piece piece) {
        if (piece == null) {
            if (this.piece != null) {
                imageView.setVisible(false);
            }
        } else {
            imageView.setVisible(true);
            String colorName = piece.getColor() == Piece.Color.WHITE ? "white" : "black";
            String typeName;
            switch (piece.getPieceType()) {
                case QUEEN:
                    typeName = "queen";
                    break;
                case ROOK:
                    typeName = "rook";
                    break;
                case KING:
                    typeName = "king";
                    break;
                case BISHOP:
                    typeName = "bishop";
                    break;
                case KNIGHT:
                    typeName = "knight";
                    break;
                default:
                    typeName = "pawn";
                    break;
            }
            imageView.setImage(new Image("/view/img/"+colorName+"/"+typeName+".png"));
        }
        this.piece=piece;
    }

    public void select(){
        getChildren().add(selection);
    }
    public void deselect(){
        getChildren().remove(selection);
    }
}
