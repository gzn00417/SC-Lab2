package P3;

import java.util.Map;
import java.util.Set;

public interface Game {

    /**
     * to generate a particular game according to the String gameType
     * @param gameType
     * @return a new chessGame or goGame
     */
    public static Game newGame(String gameType) {
        return gameType == "chess" ? (new chessGame()) : (new goGame());
    }

    /**
     * Choice 1
     * put one particular piece on the board
     * 
     * the player should be gaming
     * the piece should belong to the player and be out of the board
     * the position should be free and legally in the board
     *
     * @param player
     * @param piece
     * @param position
     * @return true if the put is legal
     */
    public boolean put(Player player, Piece piece, Position position);

    /**
     * Choice 2
     * FOR chess
     * move or put a piece in a position
     * 
     * the player should be gaming
     * the source-piece should belong to the player and the target-piece should belong to another player
     * 
     * @param player
     * @param positions
     * @return true if the move is legal
     */
    public boolean move(Player player, Position... positions);

    /**
     * Choice 3
     * capture a piece in a position
     * 
     * the player should be gaming
     * the source-piece should belong to the player and the target-piece should belong to another player
     * if the game is go, there is no target position
     * 
     * @param player
     * @param positions
     * @return true if the capture is legal
     */
    public boolean capture(Player player, Position... positions);

    /**
     * Choice 4
     * ask a position whether it is free
     * 
     * the position should be within the board
     * 
     * @param position
     * @return player if the position is occupied, null if the position is free
     */
    public Player isFree(int x, int y);

    /**
     * Choice 5
     * ask the sum of the piece on the board
     * @return a map whose keys are the players and values are the sum of the pieces of both players
     */
    public Map<Player, Integer> sumPiece();

    /**
     * Choice 6
     * skip the choosing
     */
    public void skip();

    /**
     * Choice 7
     * enter "end" to end the game initiative
     */
    public void end();

    /**
     * the origin pieces of one particular game
     * @param firstFlag
     * @return the set of pieces initialized
     */
    public Set<Piece> pieces(boolean firstFlag);

    /**
     * @param p1
     * @param p2
     * @return set the first and second players
     */
    public boolean setPlayers(Player p1, Player p2);

    /**
     * @return first player
     */
    public Player player1();

    /**
     * @return second player
     */
    public Player player2();

    /**
     * @return the type of the game
     */
    public String gameType();
}