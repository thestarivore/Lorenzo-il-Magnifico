package views.gui;


import controllers.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * Created by Mattia on 02/07/2017.
 */
public class GameBoard{

    private StackPane stackPane;
    private ScrollPane boardPane;



    public GameBoard() {
        stackPane = new StackPane();
        stackPane.setPrefSize(640,720);
        boardPane = new ScrollPane();
        boardPane.setPrefSize(640,720);

        ImageView boardImg = new ImageView("/image/gameboard_f_c.jpeg");

        boardImg.fitWidthProperty().bind(boardPane.widthProperty());
        boardImg.fitHeightProperty().bind(boardPane.heightProperty());

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GraphicalUserInterface.class.getResource("/fxml/prova.fxml"));
            AnchorPane anchorPane = loader.load();

            stackPane.getChildren().addAll(boardImg,anchorPane);

            boardPane.setContent(stackPane);

        } catch(IOException e) {
            e.printStackTrace();
        }






    }

    public ScrollPane getBoardPane() {
        return boardPane;
    }


}
