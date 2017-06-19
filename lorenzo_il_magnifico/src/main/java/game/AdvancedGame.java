package game;

import models.board.PersonalBonusTile;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class AdvancedGame extends TheGame {
    private PersonalBonusTile customBonusTile;

    public AdvancedGame() {
        super();
    }

    public PersonalBonusTile getCustomBonusTile() {
        return customBonusTile;
    }

    public void setCustomBonusTile(PersonalBonusTile customBonusTile) {
        this.customBonusTile = customBonusTile;
    }
}
