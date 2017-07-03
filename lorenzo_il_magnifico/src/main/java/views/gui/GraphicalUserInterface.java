package views.gui;

import controllers.Player;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mattia on 02/07/2017.
 */
public class GraphicalUserInterface extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;
    private GameBoard gameBoard;

    public void start(Stage primaryStage)  throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Prova");
        this.primaryStage.setMaximized(true);

        initRootLayout();

        showGraphicalUserInterface(rootLayout);
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GraphicalUserInterface.class.getResource("/fxml/GameBoardLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void showGraphicalUserInterface(BorderPane rootLayout) {

        ObservableList<Player> players = FXCollections.observableArrayList();
        Player player1 = new Player("Mattia");
        players.add(player1);
        players.add(new Player("Pio"));
        GameBoard gameBoard = new GameBoard();
        PlayerInfo playerInfo = new PlayerInfo(players);

        rootLayout.setLeft(gameBoard.getBoardPane());
        rootLayout.setRight(playerInfo.getPlayerInfoPane());





    }

    public static void main(String[] args) {
        launch(args);
    }
}
