package models.board;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class DefectTest {
    @Test
    public void addFormOther() throws Exception {
        Defect  defect = new Defect();
        defect.setDice(4);
        defect.addFormOther(defect);

    }

}