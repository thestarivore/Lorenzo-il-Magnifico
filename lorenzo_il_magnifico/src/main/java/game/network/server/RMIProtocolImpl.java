package game.network.server;


import controllers.RemotePlayer;

import game.Lobby;
import game.TheGame;
import game.network.protocol.RMIProtocol;
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
    public String[] getAvailableColors(int id) {
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
    public void setPlayerColor(int id, String color) {
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
     * Get the remote player with the required id
     * @param id integer used to indentify the players uniquely
     * @return RemotePlayer of the required player
     */
    private RemotePlayer getRemotePlayerById(int id){
        ArrayList<TheGame> games = lobby.getGames();
        RemotePlayer player = null;

        //Iterate games
        for (int i = 0; i < games.size(); i++){
            //Iterate players
            player = (RemotePlayer) games.get(i).getPlayerById(id);
        }

        return player;
    }
}
