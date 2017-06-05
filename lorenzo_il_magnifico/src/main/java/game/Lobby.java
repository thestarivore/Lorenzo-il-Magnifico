package game;


import controllers.Player;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by Paolo on 08/05/17.
 */
public class Lobby {
    private TheGame games;
    private String[] colorAvailable = {"RED", "BLUE", "YELLOW", "GREEN"};

    public Lobby(TheGame games) {
        this.games = games;
    }


    public TheGame getGames() {
        return games;
    }

    public void setGames(TheGame games) {
        this.games = games;
    }

    public String chooseColor(Player player) {
        boolean correct = false;
        System.out.println("Available Color:");
        for (int i = 0; i < 4; i++)
            System.out.println(colorAvailable[i]);
        Scanner in = new Scanner(System.in);
        String s = null;
        while (!correct) {
            System.out.println("Insert Color:");
            s = in.nextLine();
            for (int i = 0; i < 4; i++)
                if (s.equalsIgnoreCase(colorAvailable[i]))
                    correct = true;
        }
        removeColor(s);
        return s;
    }

    public void removeColor(String color) {
        for (int i=0; i < 4; i++)
            if (color.equalsIgnoreCase(colorAvailable[i]))
                colorAvailable[i]= "-";
    }



}
