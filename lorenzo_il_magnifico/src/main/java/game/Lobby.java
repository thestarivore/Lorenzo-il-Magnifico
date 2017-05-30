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

    public static void main(String[] args){
        int numberOfPlayer = 4; //must be asked

        games = new TheGame(numberOfPlayer);
    }
}
