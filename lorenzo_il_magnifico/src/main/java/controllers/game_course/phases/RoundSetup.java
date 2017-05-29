package controllers.game_course.phases;

import utility.Constants;
import views.board.Board;
import views.board.Dice;
import views.board.Tower;
import views.cards.Card;
import views.cards.DevelopmentCard;

/**
 * Created by starivore on 5/7/17.
 */
public class RoundSetup implements Phase {
    private Tower tower;
    private Dice[] dice;


    public RoundSetup() {

        this.tower = new Tower();
        this.dice = new Dice[Constants.FIXED_NUM_OF_DICE];

    }

    public void initializeRound(Board board){

        for (int i=0 ; i<Constants.FIXED_NUM_OF_TOWER ; i++) {
            tower = board.getTower(i);
            fillTower(this.tower);
        }

        dice = board.getDice();
        for (int i=0 ; i<Constants.FIXED_NUM_OF_DICE ; i++)
            dice[i].rollDice(Constants.FIXED_MIN_DICE , Constants.FIXED_MAX_DICE);

    }






    public Tower fillTower(Tower tower){

        DevelopmentCard[] cards;
        cards = new DevelopmentCard[Constants.FIXED_TOWER_CARDS];
        for (int i=0 ; i< Constants.FIXED_TOWER_CARDS ; i++)
            cards[i]=tower.getDeck().getCardFromDeck();
        for (int i=0 ; i<Constants.FIXED_TOWER_CARDS ; i++)
            tower.getSpace(i).setCard(cards[i]);
        return tower;

    }



}
