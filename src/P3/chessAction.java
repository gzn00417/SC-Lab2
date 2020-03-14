package P3;

public class chessAction implements Action {
    private final String actionType;
    private final Position[] positions;
    private final Player player;
    private final Piece piece;

    /**
     * create and finish the action
     * @param player
     * @param actionType
     * @param piece
     * @param positions
     */
    chessAction(Player player, String actionType, Piece piece, Position... positions) {
        this.player = player;
        this.positions = positions;
        this.piece = piece;
        this.actionType = actionType;
        switch (actionType) {
            case "put":
                put(piece);
                break;
            case "move":
                move();
                break;
            case "capture":
                capture();
                break;
        }
        checkRep();
    }

    /**
     * Rep:
     * actionType must be in {"put", "move", "capture"}
     * the size of positions must be 1 or 2
     * player can't be null
     * if the actionType == "put", piece can't be null
     */
    private void checkRep() {
        assert (actionType == "put" || actionType == "move" || actionType == "capture");
        assert (positions.length == 1 || positions.length == 2);
        assert (player != null);
        assert (actionType != "put" || (actionType == "put" && piece != null));
    }

    @Override
    public boolean put(Piece piece) {
        Position target = positions[0];
        return false;
    }

    @Override
    public boolean move() {
        Position source = positions[0], target = positions[1];
        return true;
    }

    @Override
    public boolean capture() {
        Position source = positions[0], target = positions[1];
        return true;
    }

    @Override
    public String actionType() {
        return this.actionType;
    }

    @Override
    public Position position() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Player player() {
        // TODO Auto-generated method stub
        return null;
    }

}