package models.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mattia on 29/05/2017.
 */
public class HarvestArea implements Serializable {
    private ActionSpace singleSpace;
    private List<ActionSpace> multipleSpace;

    /**
     * Harvest Area Action Space Constructor
     * @param numberOfPlayer
     */
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

    public ActionSpace getMultipleSingleSpace(int i) {
        return multipleSpace.get(i);
    }

    public void addMultipleSpace() {
        this.multipleSpace.add(new ActionSpace());
    }
}
