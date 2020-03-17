package P3;

public interface Action {

    /**
     * generate a new Action of one piece
     * do the action
     * @param gameType
     * @param player
     * @param actionType
     * @param piece
     * @param positions
     * @return an object of a type of Action(chessAction or goAction)
     */
    public static Action newAction(String gameType, Player player, String actionType, Piece piece,
            Position... positions) {
        return gameType.equals("chess") ? (new chessAction(player, actionType, piece, positions))
                : (new goAction(player, actionType, piece, positions));
    }

    /**
     * @param piece
     * @return true if the putting is legal
     */
    public boolean put();

    /**
     * move one piece to a chose position
     * @return true if the move is legal
     */
    public boolean move();

    /**
     * capture a piece by another piece(chess) or a group of pieces(go)
     * @return true if the capture is legal
     */
    public boolean capture();

    /**
     * ask the action's type
     * @return the action's type
     */
    public String actionType();

    /**
     * @return who does the action
     */
    public Player player();

    /**
     * @return true if the action is done successfully
     */
    public boolean askSuccess();
}