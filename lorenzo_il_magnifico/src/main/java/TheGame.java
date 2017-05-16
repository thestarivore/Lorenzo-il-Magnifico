/**
 * Created by starivore on 5/7/17.
 */
public class TheGame {
    private Period period;
    private int playersNumber;
    private FileManager fileManager;

    public Period getPeriod() {
        return period;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }
}
