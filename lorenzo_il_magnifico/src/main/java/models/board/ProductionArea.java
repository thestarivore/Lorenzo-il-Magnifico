package models.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mattia on 29/05/2017.
 */
public class ProductionArea {
    private ActionSpace singleSpace;
    private List<ActionSpace> multipleSpace;

    /**
     * Production Area Action Space Constructor
     * @param numberOfPlayer
     */
    public ProductionArea(int numberOfPlayer) {
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

    public void addMultipleSpace(ActionSpace singleSpace) {
        this.multipleSpace.add(singleSpace);
    }
}
