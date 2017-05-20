package views.board;

import views.board.trackers.Track;

/**
 * Created by starivore on 5/7/17.
 */
public class Board {
    private ActionSpace spaces;
    private TheMarket market;
    private TheCouncilPalace coincilPalace;
    private Track tracks[];

    public ActionSpace getSpaces() {
        return spaces;
    }

    public void setSpaces(ActionSpace spaces) {
        this.spaces = spaces;
    }

    public TheMarket getMarket() {
        return market;
    }

    public void setMarket(TheMarket market) {
        this.market = market;
    }

    public TheCouncilPalace getCoincilPalace() {
        return coincilPalace;
    }

    public void setCoincilPalace(TheCouncilPalace coincilPalace) {
        this.coincilPalace = coincilPalace;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }
}
