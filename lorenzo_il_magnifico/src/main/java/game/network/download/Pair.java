package game.network.download;

import java.io.Serializable;

/**
 * Created by starivore on 6/7/17.
 */
public class Pair implements Serializable {
    private String key;
    private double value;
    private DataTable reference;

    public Pair(String key, double value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public double getValue(){
        return value;
    }

    public DataTable getReference() {
        return reference;
    }

    public void setReference(DataTable reference) {
        this.reference = reference;
    }
}

