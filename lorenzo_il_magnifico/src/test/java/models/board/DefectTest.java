package models.board;

import com.sun.org.apache.regexp.internal.RE;
import models.Points;
import models.Resources;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class DefectTest {
    @Test
    public void addFormOther() throws Exception {
        Defect  defect = new Defect();
        defect.addFormOther(defect);
        assertEquals(defect.getPoints().getVictory(),0);

    }

}