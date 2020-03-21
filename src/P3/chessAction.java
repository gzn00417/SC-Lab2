package P3;

public class chessAction implements Action {
    private final String actionType;
    private Position[] positions;
    public Player player;
    public Piece piece;
    private final boolean actionSuccess; // true if the action if it succeeds

    /**
     * create and finish the action
     * @param player the operating player
     * @param actionType String of the type of the action
     * @param piece the operating piece
     * @param positions the position related to the action
     */
    chessAction(Player player, String actionType, Piece piece, Position... positions) {
        this.player = player;
        this.positions = positions;
        this.piece = piece;
        this.actionType = actionType;
        switch (actionType) {
            case "put":
                this.actionSuccess = (piece != null) && put();
                break;
            case "move":
                this.actionSuccess = move();
                break;
            case "capture":
                this.actionSuccess = capture();
                break;
            default:
                this.actionSuccess = false;
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
        if (actionType.equals("put"))
            assert (piece != null);
    }

    @Override
    public boolean put() {
        Position target = this.positions[0];
        // put requirement:
        // 1. the piece of the target can't be null
        // 2. the putting piece can't be null
        // 3. the piece must belong to the player
        if (this.piece.position() == null && target.piece() == null && player.pieces().contains(piece)) {
            this.piece.modifyPositionAs(target);
            target.modifyPieceAs(this.piece);
            return true;
        }
        return false;
    }

    @Override
    public boolean move() {
        Position source = this.positions[0], target = this.positions[1];
        // move requirement:
        // 1. the piece of the source can't be null
        // 2. the piece of the target must be null (except capturing)
        // 3. the source piece must belong to player
        if (source.piece() != null && target.piece() == null && source.piece().player().equals(player)) {
            source.piece().modifyPositionAs(target);
            target.modifyPieceAs(source.piece());
            source.modifyPieceAs(null);
            return true;
        }
        return false;
    }

    @Override
    public boolean capture() {
        Position source = this.positions[0], target = this.positions[1];
        // capture requirement:
        // 1. the target can't be null
        // 2. the source can't be null
        // 3. the target must belong to the OPPOSITE
        // 4. the source must belong to this player
        if (target.piece() != null && source.piece() != null && (!target.piece().player().equals(player))
                && source.piece().player().equals(player)) {
            target.piece().modifyPositionAs(null); // the piece capturing removed
            source.piece().modifyPositionAs(target); // captured piece move to the target
            target.modifyPieceAs(source.piece());// move the piece, this must be done before source's piece be null
            source.modifyPieceAs(null);// set the source null
            return true;
        }
        return false;
    }

    @Override
    public String actionType() {
        return this.actionType;
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