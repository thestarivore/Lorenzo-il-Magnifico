package models.data_persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;

import models.Config;
import models.board.ExcommunicationTile;
import models.board.PersonalBoard;
import models.cards.*;

import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import models.cards.Deck;

/**
 * Created by cp18393 on 11/06/17.
 */
public class FileManagerImport implements Serializable{

    /**
     * basic constructor
     * */
    public FileManagerImport(){}

    /**
     * Method to import all developmentCard from file
     * */
    public ArrayList<DevelopmentCard> acquireDevCard() {
        ArrayList<DevelopmentCard> developmentCards = new ArrayList<DevelopmentCard>();
        final Type dCardType = new TypeToken<ArrayList<DevelopmentCard>>(){}.getType();
        Gson gson = new Gson();
        String dCardFile = "lorenzo_il_magnifico/src/main/resources/DevCard.json";
        try (Reader reader = new FileReader(dCardFile)) {
            developmentCards = gson.fromJson(reader, dCardType);
            for(int i = 0; i < developmentCards.size(); i++) {
                System.out.print(developmentCards.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();



        }
        return developmentCards;

    }

    /**
     * Method to import all leaderCard from file
     **/
    public ArrayList<LeaderCard> acquireLeaderCard(){
        ArrayList<LeaderCard> leaderCards = new ArrayList<LeaderCard>();
        final Type lCardType = new TypeToken<ArrayList<LeaderCard>>(){}.getType();
        Gson gson = new Gson();
        String lCardFile = "lorenzo_il_magnifico/src/main/resources/LeaderCard.json";
        try (Reader reader = new FileReader(lCardFile)) {
            leaderCards = gson.fromJson(reader, lCardType);
            for(int i = 0; i < leaderCards.size(); i++) {
                System.out.print(leaderCards.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderCards;
}

    /**
     * Method to import the excommunicatioTiles from files
     **/
    public ArrayList<ExcommunicationTile> acquireTiles(){
        ArrayList<ExcommunicationTile> excommunicationTiles = new ArrayList<ExcommunicationTile>();
        final Type tilesType = new TypeToken<ArrayList<ExcommunicationTile>>(){}.getType();
        Gson gson = new Gson();
        String tilesFiles = "orenzo_il_magnifico/src/main/resources/ExcommunicationsTiles.json";
        try (Reader reader = new FileReader(tilesFiles)) {
            excommunicationTiles = gson.fromJson(reader, tilesType);
            for(int i = 0; i < excommunicationTiles.size(); i++) {
                System.out.print(excommunicationTiles.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excommunicationTiles;
    }

    /**
     * Method to import the configuration files
     **/
    public Config acquireConfigurations(){
        Gson gson = new Gson();
        Config configFile = new Config();
        String configFiles = "lorenzo_il_magnifico/src/main/resources/Config.json";
        try (Reader reader = new FileReader(configFiles)) {
            configFile = gson.fromJson(reader, Config.class);
                System.out.println(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configFile;
    }


    /**
     * Method to import the personal board from file
     **/
    public ArrayList<PersonalBoard> acquirePersonalBoard() {
        ArrayList<PersonalBoard> personalBoards = new ArrayList<PersonalBoard>();
        final Type boardType = new TypeToken<ArrayList<PersonalBoard>>() {
        }.getType();
        Gson gson = new Gson();
        String boardFiles = "lorenzo_il_magnifico/src/main/resources/PlayerBoard.json";
        try (Reader reader = new FileReader(boardFiles)) {
            personalBoards = gson.fromJson(reader, boardType);
            for (int i = 0; i < personalBoards.size(); i++) {
                System.out.print(personalBoards.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personalBoards;
    }
}