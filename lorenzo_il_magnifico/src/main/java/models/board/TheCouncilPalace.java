package models.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class TheCouncilPalace implements Serializable {
    private List<ActionSpace> spaces;


    public TheCouncilPalace() {
        this.spaces = new ArrayList<ActionSpace>();
    }

    public List<ActionSpace> getSpaces() {
        return spaces;
    }

    public ActionSpace getSpace(int i) {
        return spaces.get(i);
    }

    public void addSpaces() {
        this.spaces.add(new ActionSpace());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TheCouncilPalace)) return false;

        TheCouncilPalace that = (TheCouncilPalace) o;

        return spaces != null ? spaces.equals(that.spaces) : that.spaces == null;
    }

    @Override
    public int hashCode() {
        return spaces != null ? spaces.hashCode() : 0;
    }
}
