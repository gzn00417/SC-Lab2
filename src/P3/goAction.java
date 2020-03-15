package P3;

public class goAction implements Action {
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
    goAction(Player player, String actionType, Piece piece, Position... positions) {
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
        assert (actionType == "put" || actionType == "move" || actionType == "capture");
        assert (positions.length == 1 || positions.length == 2);
        assert (player != null);
        assert (actionType != "put" || (actionType == "put" && piece != null));
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
        return;
    }

    @Override
    public void move() {
        Position target = positions[0];
        // move requirement:
        // the piece of the target can't be null
        if (target.piece() == null) {
            Piece newPiece = player.freePiece();
            newPiece.modifyPositionAs(target);
            target.modifyPieceAs(newPiece);
            actionSuccess = true;
            return;
        }
        actionSuccess = false;
    }

    @Override
    public void capture() {
        Position target = positions[0];
        // capturing requirement:
        // 1. the target can't have no piece
        // 2. the owner of the piece can't be the same as the executor
        if (target.piece() != null && target.piece().player() != player) {
            // delete piece first
            // because if not, piece will miss
            target.piece().modifyPositionAs(null);
            target.modifyPieceAs(null);
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