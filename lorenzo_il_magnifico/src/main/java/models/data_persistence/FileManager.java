package models.data_persistence;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import controllers.Player;
import com.google.gson.Gson;
import models.cards.Deck;
import models.cards.DevelopmentCard;
import models.cards.LeaderCard;
import java.util.ArrayList;
/**
 * Created by starivore on 5/16/17.
 */

public class FileManager {
    private Deck deck;
    private DevelopmentCard developmentCard;
    private LeaderCard leaderCard;

    DevelopmentCard developmentCard = new DevelopmentCard();
    LeaderCard leaderCard = new LeaderCard();
    Deck deck = new Deck();


    public Deck CreateDeck(Deck deck){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a path: ");
        final String fileName = reader.nextLine();
       // DevelopmentCard developmentCard = createDevelopmentCard();
        Gson gson = new Gson();
       /* try (FileWriter writer = new FileWriter(fileName)) {

            gson.toJson(developmentCard, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try (Reader fileReader = new FileReader(fileName)) {

            // Convert JSON to Java Object
            DevelopmentCard developmentCard = gson.fromJson(fileReader, DevelopmentCard.class);
            deck.developmentCards.add(developmentCard);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return deck;

    }


   /* private static DevelopmentCard createDevelopmentCard() {
        DevelopmentCard = new DevelopmentCard();
        return DevelopmentCard;

    }*/


    //   public void Serialization(Object object) {
        // }

        //public Object Dserialization() {
        //   return null;
        // }

}