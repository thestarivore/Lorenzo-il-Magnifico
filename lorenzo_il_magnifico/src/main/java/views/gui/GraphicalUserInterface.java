package views.gui;

import controllers.Player;
import game.TheGame;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.board.Board;
import models.board.Dice;
import models.board.FamilyMember;
import models.cards.DevelopmentCard;

import java.io.IOException;

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
        Board board = new Board(4);
        board.setPeriod(0);
        board.setPhase("prova");
        board.setRound(2);
        //board.setPlayerInTurn("mattia");
        board.getTower(1).getSpace(0).setFamilyMember(new FamilyMember());
        board.getTower(1).getSpace(0).getFamilyMember().setPlayerColor(TheGame.COLORS.RED);
        board.getTower(1).getSpace(0).getFamilyMember().setDiceColor(Dice.COLORS.WHITE);
        board.getTower(0).getSpace(0).setCard(new DevelopmentCard());
        board.getTower(1).getSpace(0).setOccupied();

        GameBoard gameBoard = new GameBoard(board);
        PlayerInfo playerInfo = new PlayerInfo(players, board);

        rootLayout.setLeft(gameBoard.getBoardPane());
        rootLayout.setRight(playerInfo.getPlayerInfoPane());

        players.get(0).getRes().setCoins(25);

        players.get(0).getRes().setStones(23);

        board.setPeriod(0);
        board.setPhase("prova");
        board.setRound(2);
        //board.setPlayerInTurn("mattia");





    }

    public static void main(String[] args) {
        launch(args);
    }
}
