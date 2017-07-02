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


}
