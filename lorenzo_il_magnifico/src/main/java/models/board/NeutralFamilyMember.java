package models.board;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class NeutralFamilyMember{

    protected String playerName;
    protected int playerID;
    protected int value;
    protected boolean isUsed;
    protected FAMILYMEMBERCOLORS familyMemberColors;


    public enum FAMILYMEMBERCOLORS {
        NEUTRAL("NEUTRAL"),
        ORANGE("ORANGE"),
        WHITE("WHITE"),
        BLACK("BLACK"),
        ;

        String color;

        FAMILYMEMBERCOLORS(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }


    }

    public NeutralFamilyMember(String playerName, int playerID) {

        this.playerName = playerName;
        this.playerID = playerID;
        this.value = 0;
        this.isUsed = false;
        this.familyMemberColors = FAMILYMEMBERCOLORS.NEUTRAL;

    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addValue(int servantValue) {
        this.value+=servantValue;
    }

    public boolean getUsed() {
        return isUsed;
    }

    public void setUsed() {
        this.isUsed = true;
    }

    public FAMILYMEMBERCOLORS getFamilyMemberColors() {
        return familyMemberColors;
    }


}
