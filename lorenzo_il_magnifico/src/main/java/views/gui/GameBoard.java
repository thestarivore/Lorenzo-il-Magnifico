package views.gui;


import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.board.Board;
import models.board.Dice;

import java.io.IOException;

/**
 * Created by Mattia on 02/07/2017.
 */
public class GameBoard{

    private StackPane stackPane;
    private ScrollPane boardPane;



    public GameBoard(Board board) {
        stackPane = new StackPane();
        stackPane.setPrefSize(640,720);
        boardPane = new ScrollPane();
        boardPane.setPrefSize(640,720);

        ImageView boardImg = new ImageView("/image/gameboard_f_c.jpeg");

        boardImg.fitWidthProperty().bind(boardPane.widthProperty());
        boardImg.fitHeightProperty().bind(boardPane.heightProperty());

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GraphicalUserInterface.class.getResource("/fxml/CardPane.fxml"));
            AnchorPane anchorPane = loader.load();

            stackPane.getChildren().addAll(boardImg, anchorPane);
            stackPane.getChildren().get(1).setTranslateX(-25);
            stackPane.getChildren().get(1).setTranslateY(-140);

            showTowerCards(anchorPane, board);
            showTowerOccupation(anchorPane, board);

        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GraphicalUserInterface.class.getResource("/fxml/HarvestProductionPane.fxml"));
            AnchorPane anchorPane = loader.load();

            stackPane.getChildren().add(anchorPane);
            stackPane.getChildren().get(2).setTranslateX(-180);
            stackPane.getChildren().get(2).setTranslateY(270);


        } catch(IOException e) {
            e.printStackTrace();
        }

        boardPane.setContent(stackPane);






    }

    public void showTowerCards(AnchorPane anchorPane, Board board) {
        GridPane gridPane = (GridPane)anchorPane.getChildren().get(0);


        for (int i = 0; i < Board.FIXED_NUMBER_OF_TOWER; i++) {
            for (int j = 0; j < Board.CARDS_PER_TOWER; j++) {

                if (!board.getTower(i).getSpace(j).getOccupied()) {
                    String url = GuiUtil.getDevCardURL(board.getTower(i).getSpace(j).getCard().getName());
                    ImageView image = new ImageView(url);
                    image.setPreserveRatio(true);
                    image.setFitHeight(80);
                    gridPane.add(image, i * 2, j);

                }
            }
        }

    }

    public void showTowerOccupation(AnchorPane anchorPane, Board board) {
        GridPane gridPane = (GridPane)anchorPane.getChildren().get(0);

        for (int i = 0; i < Board.FIXED_NUMBER_OF_TOWER; i++) {
            for (int j = 0; j < Board.CARDS_PER_TOWER; j++) {

                if(board.getTower(i).getSpace(j).getOccupied()) {

                    if(board.getTower(i).getSpace(j).getFamilyMember().getDiceColor() == Dice.COLORS.NEUTER ) {
                        String color = board.getTower(i).getSpace(j).getFamilyMember().getPlayerColor().getColor();
                        Circle famMemb = new Circle(100,100, 10);
                        Circle neuter = new Circle(100,100,8);
                        gridPane.add(famMemb, (i * 2) + 1, j);
                        gridPane.add(neuter, (i * 2) + 1, j);
                        famMemb.setTranslateX(17);
                        famMemb.setTranslateY(-5);
                        famMemb.setFill(Color.web(color));
                        neuter.setTranslateX(19);
                        neuter.setTranslateY(-5);
                        neuter.setFill(Color.WHITE);

                    } else {
                        String color = board.getTower(i).getSpace(j).getFamilyMember().getPlayerColor().getColor();
                        Circle famMemb = new Circle(100, 100, 10);
                        gridPane.add(famMemb, (i * 2) + 1, j);
                        famMemb.setTranslateX(17);
                        famMemb.setTranslateY(-5);
                        famMemb.setFill(Color.web(color));
                    }
                }
            }
        }
    }

    public void showHarvestProductionOccupation(AnchorPane anchorPane, Board board) {
        GridPane gridPane = (GridPane)anchorPane.getChildren().get(0);





    }

    public ScrollPane getBoardPane() {
        return boardPane;
    }

}
