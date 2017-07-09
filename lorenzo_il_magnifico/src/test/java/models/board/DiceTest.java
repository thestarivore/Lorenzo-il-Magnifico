package models.board;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class DiceTest {
    @Test
    public void rollDice() throws Exception {
        Dice test = new Dice();
        int a;
        a = test.rollDice(1,6);
        assert a<= 6;
        assert a>=1;
    }

}