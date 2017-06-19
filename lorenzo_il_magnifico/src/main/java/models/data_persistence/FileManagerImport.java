package models.data_persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import models.cards.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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


    public DevelopmentCardDeck getDevelopmentCards() {
        Gson gson = new Gson();
        String dCardFile = null;
        try (Reader reader = new FileReader(dCardFile)) {
            DevelopmentCardDeck developmentCards = gson.fromJson(reader, new TypeToken<DevelopmentCardDeck>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developmentCards;
    }

    public LeaderCardDeck getLeaderCards() {
        String lCardFile = null;
        Gson gson = new Gson();
        try (Reader reader = new FileReader(lCardFile)) {
            LeaderCardDeck leaderCards = gson.fromJson(reader, new TypeToken<LeaderCardDeck>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderCards;
    }
    public void callFileManager(){
        getLeaderCards();
    }


}

