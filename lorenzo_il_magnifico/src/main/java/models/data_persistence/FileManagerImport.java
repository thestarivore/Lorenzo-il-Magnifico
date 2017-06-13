package models.data_persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.cards.DevelopmentCard;
import models.cards.LeaderCard;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cp18393 on 11/06/17.
 */
public class FileManagerImport {

    public ArrayList<DevelopmentCard> getDevelopmentCards() {

        Gson gson = new Gson();
        try (Reader reader = new FileReader("/filename")) {
            ArrayList<DevelopmentCard> developmentCards = gson.fromJson(reader, new TypeToken<ArrayList<DevelopmentCard>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developmentCards;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        String lCardFile = null;
        Gson gson = new Gson();
        try (Reader reader = new FileReader(lCardFile)) {
            ArrayList<LeaderCard> leaderCards = gson.fromJson(reader, new TypeToken<ArrayList<LeaderCard>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderCards;
    }

    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<DevelopmentCard> developmentCards;
}

