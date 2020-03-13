package P3;

public interface Action {
    /**
     * generate a new Action of one piece
     * @param gameType
     * @param actionType
     * @return
     */
    public static Action newAction(String gameType, String actionType) {
        return gameType == "chess" ? (new chessAction(actionType)) : (new goAction(actionType));
    }

    /**
     * move one piece to a chose position
     * @param positions
     * @return true if the move is legal
     */
    public boolean move(Position... positions);

    /**
     * capture a piece by another piece(chess) or a group of pieces(go)
     * @param positions
     * @return true if the capture is legal
     */
    public boolean capture(Position... positions);

    /**
     * ask the action's type
     * @return the action's type
     */
    public String actionType();
}