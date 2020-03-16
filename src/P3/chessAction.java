package P3;

public class chessAction implements Action {
    private final String actionType;
    private final Position[] positions;
    private final Player player;
    private final Piece piece;
    private boolean actionSuccess;

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
                put();
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
        assert (actionType.equals("put") || actionType.equals("move") || actionType.equals("capture"));
        assert (positions.length == 1 || positions.length == 2);
        assert (player != null);
        assert (!actionType.equals("put") || (actionType.equals("put") && piece != null));
    }

    @Override
    public void put() {
        Position target = positions[0];
        // put requirement:
        // 1. the piece of the target can't be null
        // 2. the putting piece can't be null
        if (this.piece.position() == null && target.piece() != null) {
            this.piece.modifyPositionAs(target);
            target.modifyPieceAs(this.piece);
            actionSuccess = true;
            return;
        }
        actionSuccess = false;
    }

    @Override
    public void move() {
        Position source = positions[0], target = positions[1];
        // move requirement:
        // 1. the piece of the source can't be null
        // 2. the piece of the target must be null (except capturing)
        if (source.piece() != null && target.piece() == null) {
            source.piece().modifyPositionAs(target);
            target.modifyPieceAs(source.piece());
            actionSuccess = true;
            return;
        }
        actionSuccess = false;
    }

    @Override
    public void capture() {
        Position source = positions[0], target = positions[1];
        // capture requirement:
        // 1. the target can't be null
        // 2. the source can't be null
        if (target.piece() != null && source.piece() != null) {
            target.piece().modifyPositionAs(null); // the piece captured removed
            source.piece().modifyPositionAs(target); // source piece move to the target
            target.modifyPieceAs(source.piece());// move the piece, this must be done before source's piece be null
            source.modifyPieceAs(null);// set the source null
            actionSuccess = true;
            return;
        }
        actionSuccess = false;
    }

    @Override
    public String actionType() {
        return this.actionType;
    }

    @Override
    public Position position() {
        return this.position();
    }

    @Override
    public Player player() {
        return this.player;
    }

    @Override
    public boolean askSuccess() {
        return this.actionSuccess;
    }

}