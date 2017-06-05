package models;

import models.board.ActionSpace;
import models.board.Board;
import models.board.Dice;
import models.board.NeutralFamilyMember;
import models.data_persistence.FileManager;

/**
 * Created by starivore on 5/20/17.
 *
 * This is the Model
 * from the MVC pattern(Model View Controller)
 *
 * The Model performs all the calculations needed and that is it.
 * It doesn't know the View exists
 */
public class GameFacadeModel {
    private Board board;
    private ActionSpace space;
    private Dice dice;
    private NeutralFamilyMember familyMember;




}
