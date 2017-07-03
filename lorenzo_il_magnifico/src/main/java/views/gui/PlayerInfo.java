package views.gui;

import controllers.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import models.board.Board;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

/**
 * Created by Mattia on 03/07/2017.
 */
public class PlayerInfo {

    private AnchorPane playerInfoPane;
    private ObservableList<Player> players = FXCollections.observableArrayList();
    private Board board;

    public PlayerInfo(ObservableList<Player> players, Board board) {

        this.players = players;
        this.board = board;

        playerInfoPane = new AnchorPane();
        playerInfoPane.setPrefSize(640,720);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GraphicalUserInterface.class.getResource("/fxml/PlayerInfo.fxml"));
            PlayerInfoController playerInfoController = new PlayerInfoController(this);
            loader.setController(playerInfoController);
            playerInfoPane = loader.load();



            System.out.println("anche qui");


        } catch(IOException e) {
            e.printStackTrace();
        }


    }

    public AnchorPane getPlayerInfoPane() {
        return playerInfoPane;
    }

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }


}
