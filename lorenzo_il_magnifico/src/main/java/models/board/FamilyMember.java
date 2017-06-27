package models.board;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class FamilyMember extends NeutralFamilyMember{

    private Dice dice;

    public FamilyMember(String playerName, int playerID, int i) {
        super(playerName, playerID);

        switch (i) {
            case 0:
                this.familyMemberColors = FAMILYMEMBERCOLORS.ORANGE;
                break;
            case 1:
                this.familyMemberColors = FAMILYMEMBERCOLORS.WHITE;
                break;
            default:
                this.familyMemberColors = FAMILYMEMBERCOLORS.BLACK;
                break;
        }
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

}
