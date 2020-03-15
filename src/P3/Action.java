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
        return gameType == "chess" ? (new chessAction(player, actionType, piece, positions))
                : (new goAction(player, actionType, piece, positions));
    }

    /**
     * @param piece
     * @return true if the putting is legal
     */
    public void put();

    /**
     * move one piece to a chose position
     * @return true if the move is legal
     */
    public void move();

    /**
     * capture a piece by another piece(chess) or a group of pieces(go)
     * @return true if the capture is legal
     */
    public void capture();

    /**
     * ask the action's type
     * @return the action's type
     */
    public String actionType();

    /**
     * @return the position which action appears
     */
    public Position position();

    /**
     * @return who does the action
     */
    public Player player();

    /**
     * @return true if the action is done successfully
     */
    public boolean askSuccess();
}