package views.board;

import java.awt.*;
import java.util.Random;

/**
 * Created by starivore on 5/7/17.
 */
public class Dice {
    private Color color;
    private int number;

    public int rollDice(int min , int max){
        Random rand= new Random();
        this.number=rand.nextInt((max-min)+1)+min;
        return this.number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}