package views.gui;

import controllers.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import models.board.Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mattia on 03/07/2017.
 */
public class PlayerInfoController {

    @FXML
    private MenuButton menuButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label woodsLabel;

    @FXML
    private Label stonesLabel;

    @FXML
    private Label coinsLabel;

    @FXML
    private Label servantsLabel;

    @FXML
    private Label victoryPointsLabel;

    @FXML
    private Label militaryPointsLabel;

    @FXML
    private Label faithPointsLabel;

    @FXML
    private Label gameTurnLabel;

    @FXML
    private Label periodLabel;

    @FXML
    private Label roundLabel;

    @FXML
    private Label phaseLabel;



    private ObservableList<Player> players;

    private Board board;




    /**
     * The constructor.
     */
    public PlayerInfoController(PlayerInfo playerInfo) {
        System.out.println("sono qui");
        players = playerInfo.getPlayers();
        board = playerInfo.getBoard();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        showPlayerInfo(null);

        for (int i = 0; i < players.size(); i++)
            menuButton.getItems().add(new MenuItem(players.get(i).getName()));

        handleMenuButton(menuButton);

        showGameInfo(board);

    }




    public void showPlayerInfo(Player player) {
        if (player != null) {
            //Fill the labels with Player information.
            nameLabel.setText(player.getName());
            woodsLabel.setText(String.valueOf(player.getRes().getWoods()));
            stonesLabel.setText(String.valueOf(player.getRes().getStones()));
            coinsLabel.setText(String.valueOf(player.getRes().getCoins()));
            servantsLabel.setText(String.valueOf(player.getRes().getServants()));
            victoryPointsLabel.setText(String.valueOf(player.getPoints().getVictory()));
            militaryPointsLabel.setText(String.valueOf(player.getPoints().getMilitary()));
            faithPointsLabel.setText(String.valueOf(player.getPoints().getFaith()));
        } else {
            //player is null, remove all information.
            nameLabel.setText("");
            woodsLabel.setText("");
            stonesLabel.setText("");
            coinsLabel.setText("");
            servantsLabel.setText("");
            victoryPointsLabel.setText("");
            militaryPointsLabel.setText("");
            faithPointsLabel.setText("");
        }

    }

    public void showGameInfo(Board board) {
        if (board != null) {
            gameTurnLabel.setText(board.getPlayerIsTurn());
            periodLabel.setText(String.valueOf(board.getPeriod()));
            roundLabel.setText(String.valueOf(board.getRound()));
            phaseLabel.setText(board.getPhase());
        } else {
            gameTurnLabel.setText("");
            periodLabel.setText("");
            roundLabel.setText("");
            phaseLabel.setText("");
        }
    }

    public void handleMenuButton(MenuButton menuButton) {

            List<MenuItem> tempItems = menuButton.getItems();

            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                tempItems.get(i).setOnAction(event -> showPlayerInfo(player));
            }

    }


}
