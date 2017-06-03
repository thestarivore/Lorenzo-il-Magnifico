package models.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mattia on 29/05/2017.
 */
public class HarvestArea {
    private ActionSpace singleSpace;
    private List<ActionSpace> multipleSpace;

    public HarvestArea(int numberOfPlayer) {
        this.singleSpace = new ActionSpace();
        if (numberOfPlayer > 2)
        this.multipleSpace = new ArrayList<ActionSpace>();
    }

    public ActionSpace getSingleSpace() {
        return singleSpace;
    }

    public void setSingleSpace(ActionSpace singleSpace) {
        this.singleSpace = singleSpace;
    }

    public List<ActionSpace> getMultipleSpace() {
        return multipleSpace;
    }

    public void setMultipleSpace(List<ActionSpace> multipleSpace) {
        this.multipleSpace = multipleSpace;
    }
}
