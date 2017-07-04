package controllers.effects;

import java.io.Serializable;

/**
 * Created by cp18393 on 04/07/17.
 */
public class ActionValueFour extends ImmediateEffect implements Serializable {
    private int dice;

    public ActionValueFour(){
        this.dice=4;
    }


}
