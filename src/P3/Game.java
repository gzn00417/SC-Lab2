package P3;

public interface Game {

    /**
     * to generate a particular game according to the String gameType
     * @param gameType
     * @return a new chessGame or goGame
     */
    public static Game newGame(String gameType) {
        return gameType == "chess" ? (new chessGame()) : (new goGame());
    }

    public boolean move();

    public boolean capture();

    public boolean isFree();

    public int sumPiece();

    /**
     * skip the choosing
     */
    public void skip();

    /**
     * end the game initiative
     */
    public void end();
}