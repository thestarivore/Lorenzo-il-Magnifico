package models.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mattia on 29/05/2017.
 */
public class ProductionArea implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductionArea)) return false;

        ProductionArea that = (ProductionArea) o;

        if (singleSpace != null ? !singleSpace.equals(that.singleSpace) : that.singleSpace != null) return false;
        return multipleSpace != null ? multipleSpace.equals(that.multipleSpace) : that.multipleSpace == null;
    }

    @Override
    public int hashCode() {
        int result = singleSpace != null ? singleSpace.hashCode() : 0;
        result = 31 * result + (multipleSpace != null ? multipleSpace.hashCode() : 0);
        return result;
    }
}
