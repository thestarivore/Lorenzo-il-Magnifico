package game;

/**
 * Created by Paolo on 08/05/17.
 */
public class Lobby {
    private static TheGame games;

    public TheGame getGames() {
        return games;
    }

    public void setGames(TheGame games) {
        this.games = games;
    }

    static public void main(){
        int numberOfPlayer = 2; //must be asked

        games = new TheGame(numberOfPlayer);
    }
}
