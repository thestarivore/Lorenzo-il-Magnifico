package models.data_persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import models.cards.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import models.cards.Deck;

/**
 * Created by cp18393 on 11/06/17.
 */
public class FileManagerImport {

    private Deck deck;
    private LeaderCardDeck leaderCards;
    private DevelopmentCardDeck developmentCards;



    public static void main(String[] args) {

    final Type dCardType = new TypeToken<ArrayList<DevelopmentCard>>(){}.getType();
        Gson gson = new Gson();
        String dCardFile = "/Users/cp18393/Desktop/LeaderCard.json";
        try (Reader reader = new FileReader(dCardFile)) {
            List<DevelopmentCard> list = gson.fromJson(reader, dCardType);
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


  /*  public LeaderCardDeck getLeaderCards() {
        String lCardFile = null;
        Gson gson = new Gson();
        try (Reader reader = new FileReader(lCardFile)) {
            LeaderCardDeck leaderCards = gson.fromJson(reader, new TypeToken<LeaderCardDeck>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderCards;

    public void callFileManager(){
        getLeaderCards();
    }
*/

}

