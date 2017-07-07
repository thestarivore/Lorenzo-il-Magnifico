package models.board;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Dice implements Serializable {
    private Color color;
    private int number;

    /**
     * Enum - Possible Dice's colors constants.
     * Actually the neuter dice does't exist, but there is one family
     * member who is neuter and has no color.
     */
    public enum COLORS{
        NEUTER("NEUTER"),
        WHITE("WHITE"),
        BLACK("BLACK"),
        ORANGE ("ORANGE"),
        ;

        String color;

        /**
         * Basic constructor
         */
        COLORS(String color) {
            this.color = color;
        }

        /**
         * Get color
         * @return String of the color
         */
        public String getColor(){
            return color;
        }
    }

    /**
     * Basic Dice Constructor
     */
    public Dice() {
        this.color = null;
        this.number = 0;
    }

    public int rollDice(int min , int max){
        Random rand= new Random();
        this.number=rand.nextInt((max-min)+1)+min;
        return this.number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(int i) {
        switch(i) {
            case 0:
                this.color = Color.BLACK;
                break;
            case 1:
                this.color = Color.ORANGE;
                break;
            default:
                this.color = Color.WHITE;
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references.
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dice)) return false;

        Dice dice = (Dice) o;

        if (number != dice.number) return false;
        return color != null ? color.equals(dice.color) : dice.color == null;
    }

    /**
     * Returns a hash code value for the object.
     * <p>
     * As much as is reasonably practical, the hashCode method defined
     * does return distinct integers for distinct objects.
     * <p>
     * @return  a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + number;
        return result;
    }
}