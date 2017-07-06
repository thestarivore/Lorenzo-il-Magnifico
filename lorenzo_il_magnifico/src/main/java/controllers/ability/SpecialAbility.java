package controllers.ability;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class SpecialAbility {

    private Gain gain;

    SpecialAbility(){
        this.gain=new Gain();
    }

    public Gain getGain() {
        return gain;
    }

    public void setGain(Gain gain) {
        this.gain = gain;
    }
}
