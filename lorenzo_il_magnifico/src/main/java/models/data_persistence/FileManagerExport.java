package models.data_persistence;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.io.FileWriter;
import models.cards.DevelopmentCard;
import models.cards.LeaderCard;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Eduard Chirica on 5/16/17.
 */

public class FileManagerExport{

    public static void main(String[] args) throws IOException {

        DevelopmentCard developmentCard = createDevelopmentCard();
        LeaderCard leaderCard = createLeaderCard();

    }

    private static DevelopmentCard createDevelopmentCard() {

        DevelopmentCard developmentCard = new DevelopmentCard();
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("/filename")) {
            gson.toJson(developmentCard, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return developmentCard;
    }

    private static LeaderCard createLeaderCard() {
        Gson gson = new Gson();
        LeaderCard leaderCard = new LeaderCard();
        try (FileWriter writer = new FileWriter("/filename")) {
            gson.toJson(leaderCard, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return leaderCard;
    }
    private String getPath(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci percorso");
        String path = sc.next();
        return path;
    }
}