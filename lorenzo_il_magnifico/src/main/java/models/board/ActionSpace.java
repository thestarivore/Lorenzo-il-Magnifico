package models.board;

import models.Resources;
import models.cards.DevelopmentCard;

/**
 * Created by starivore on 5/7/17.
 */
public class ActionSpace {
    private DevelopmentCard card;
    private Resources bonus;
    private NeutralFamilyMember familyMember;
    private int flag = 0;

    public ActionSpace() {
        this.card = new DevelopmentCard();
        this.bonus = new Resources();
        this.familyMember = new FamilyMember();
    }

    public DevelopmentCard getCard() {
        return card;
    }

    public void setCard(DevelopmentCard card) {
        this.card = card;
    }

    public Resources getBonus() {
        return bonus;
    }

    public void setBonus(Resources bonus) {
        this.bonus = bonus;
    }

    public NeutralFamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(NeutralFamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag() {
        this.flag = 1;
    }


}
