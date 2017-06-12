package controllers.game_course.phases;

import utility.Constants;
import models.board.Board;
import models.board.Dice;
import models.board.Tower;
import models.cards.DevelopmentCard;

import java.util.ArrayList;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class RoundSetup implements Phase {
    private Tower tower;
    private Dice[] dice;



    public boolean initializeRound(Board board){
        boolean endRoundSetup = false;

        while(!endRoundSetup) {
            for (int i = 0; i < Constants.FIXED_NUM_OF_TOWER; i++) {
                tower = board.getTower(i);
                fillTower(tower);
            }

            dice = board.getDice();
            for (int i = 0; i < Constants.FIXED_NUM_OF_DICE; i++)
                dice[i].rollDice(Constants.FIXED_MIN_DICE, Constants.FIXED_MAX_DICE);
            endRoundSetup = true;
        }

        return endRoundSetup;
    }






    public void fillTower(Tower tower){

        DevelopmentCard[] cards;
        cards = new DevelopmentCard[Constants.FIXED_TOWER_CARDS];
        for (int i=0 ; i< Constants.FIXED_TOWER_CARDS ; i++) {
            cards[i] = new DevelopmentCard();
            cards[i] = tower.getDeck().getCardFromDeck();
        }

        for (int i=0 ; i<Constants.FIXED_TOWER_CARDS ; i++)
            tower.getSpace(i).setCard(cards[i]);

    }



}
