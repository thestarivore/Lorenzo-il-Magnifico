package models.data_persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;

import models.Config;
import models.board.ExcommunicationTile;
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
    
    private ArrayList<LeaderCard> leaderCards = new ArrayList<LeaderCard>();
    private ArrayList<DevelopmentCard> developmentCards = new ArrayList<DevelopmentCard>();
    private ArrayList<ExcommunicationTile> excommunicationTiles = new ArrayList<ExcommunicationTile>();
    private Config config;



     //public static void main(String[] args) {
    public ArrayList<DevelopmentCard> acquireDevCard(){
       // ArrayList<DevelopmentCard> developmentCards = new ArrayList<DevelopmentCard>();
        final Type dCardType = new TypeToken<ArrayList<DevelopmentCard>>(){}.getType();
        Gson gson = new Gson();
        String dCardFile = "lorenzo_il_magnifico/src/main/resources/DevCard.json";
        try (Reader reader = new FileReader(dCardFile)) {
            ArrayList<DevelopmentCard> list = gson.fromJson(reader, dCardType);
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
            developmentCards = list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*for(int i = 0; i < developmentCards.size(); i++) {
            System.out.print(developmentCards.get(i));
        }*/
        return developmentCards;

    }



    public ArrayList<LeaderCard> acquireLeaderCard(){
        //ArrayList<DevelopmentCard> developmentCards = new ArrayList<DevelopmentCard>();
        final Type lCardType = new TypeToken<ArrayList<LeaderCard>>(){}.getType();
        Gson gson = new Gson();
        String lCardFile = "lorenzo_il_magnifico/src/main/resources/LeaderCard.json";
        try (Reader reader = new FileReader(lCardFile)) {
            ArrayList<LeaderCard> list = gson.fromJson(reader, lCardType);
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
            leaderCards=list;
        } catch (IOException e) {
            e.printStackTrace();
        }
         /*for(int i = 0; i < cards.size(); i++) {
            System.out.print(cards.get(i));
        }*/
        return leaderCards;
}

    public ArrayList<ExcommunicationTile> acquireTiles(){
        //ArrayList<E> developmentCards = new ArrayList<DevelopmentCard>();
        final Type tilesType = new TypeToken<ArrayList<ExcommunicationTile>>(){}.getType();
        Gson gson = new Gson();
        String tilesFiles = "lorenzo_il_magnifico/src/main/resources/LeaderCard.json";
        try (Reader reader = new FileReader(tilesFiles)) {
            ArrayList<ExcommunicationTile> list = gson.fromJson(reader, tilesType);
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
            excommunicationTiles=list;
        } catch (IOException e) {
            e.printStackTrace();
        }
         /*for(int i = 0; i < cards.size(); i++) {
            System.out.print(cards.get(i));
        }*/
        return excommunicationTiles;
    }

    //public static void main(String[] args){
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
}

