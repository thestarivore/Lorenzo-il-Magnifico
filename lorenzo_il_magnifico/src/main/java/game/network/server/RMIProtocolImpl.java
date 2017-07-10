package game.network.server;


import controllers.GameFacadeController;
import controllers.Player;
import controllers.RemotePlayer;

import controllers.game_course.Action;
import game.Lobby;
import game.TheGame;
import game.network.protocol.RMIProtocol;
import models.board.Board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 * Created by starivore on 6/18/17.
 */
public class RMIProtocolImpl  extends UnicastRemoteObject implements RMIProtocol{
    private Lobby lobby;

    /**
     * Basic Constructor
     * @throws RemoteException
     */
    protected RMIProtocolImpl(Lobby lobby) throws RemoteException {
        this.lobby = lobby;
    }


    /**
     * Set instance from remote client. To put it more simply,
     * add a new Remote player with the remote name and id, to the Lobby's
     * list of players.
     * @param name
     * @param id
     * @throws RemoteException
     */
    @Override
    public void setInstance(String name, int id) throws RemoteException {
        // Create a new player associated to this client and add it
        // to the game through the Lobby
        RemotePlayer newPlayer = new RemotePlayer(name);
        newPlayer.setID(id);
        lobby.newPlayerArrived(newPlayer);
    }

    /**
     * Get available colors so that the client can choose.
     * @param id
     */
    @Override
    public String[] getAvailableColors(int id) throws RemoteException{
        //Get remote player by id
        RemotePlayer player = getRemotePlayerById(id);

        //Get a color list and build a color array
        String[] colorArray = new String[TheGame.MAXIMUM_PLAYERS_NUMBER];
        ArrayList<String> colors = player.getGameReference().getAvailableColorsStrings();
        for(int i = 0; i<colors.size(); i++)
            colorArray[i] = colors.get(i);

        return colorArray;
    }

    /**
     * Set client chosen color.
     * @param id
     * @param color
     */
    @Override
    public void setPlayerColor(int id, String color) throws RemoteException{
        //Get Color
        TheGame.COLORS newColor = TheGame.COLORS.valueOf(color);

        //Get remote player by id
        RemotePlayer player = getRemotePlayerById(id);

        //Select players color
        player.setColor(newColor);

        //Remove color from it's game
        player.getGameReference().removeColor(newColor);
    }

    /**
     * Get Updated Board
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public Board getBoardUpdates(int id) throws RemoteException {
        return getController(id).getBoard();
    }

    /**
     * Get Updates on player whose turn is
     * @param id
     * @return
     */
    @Override
    public Player getPlayersTurn(int id) throws RemoteException {
        return getController(id).getPlayerInTurn();
    }

    /**
     * Send Action done
     * @param id
     * @param action
     */
    @Override
    public RemotePlayer sendAction(int id, Action action) throws RemoteException{
        //Send Action to the controller so that he can manage it
        getController(id).managePlayerAction(action);

        return getRemotePlayerById(id);
    }

    /**
     * Get the remote player with the required id
     * @param id integer used to indentify the players uniquely
     * @return RemotePlayer of the required player
     */
    private RemotePlayer getRemotePlayerById(int id) throws RemoteException{
        ArrayList<TheGame> games = lobby.getGames();
        RemotePlayer player = null;

        //Iterate games
        for (int i = 0; i < games.size(); i++){
            //Iterate players
            player = (RemotePlayer) games.get(i).getPlayerById(id);
        }

        return player;
    }

    /**
     * Get controller from user ID
     * @param id of the player
     * @return GameFacadeController instance
     */
    private GameFacadeController getController(int id) throws RemoteException{
        //Get remote player by id
        RemotePlayer player = getRemotePlayerById(id);

        //Remove color from it's game
        return  player.getGameReference().getTheController();
    }
}
