package P3;

public class goAction implements Action {
    private final String actionType;
    public Position[] positions;
    public Player player;
    public Piece piece;
    private final boolean actionSuccess;

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
        Position target = this.positions[0];
        // move requirement:
        // 1. the chosen piece can't be null
        // 2. the target must be null
        if (target.piece() == null) {
            Piece newPiece = player.freePiece();
            newPiece.modifyPositionAs(target);
            target.modifyPieceAs(newPiece);
            return true;
        }
        return false;
    }

    @Override
    public boolean capture() {
        Position target = this.positions[0];
        // capturing requirement:
        // 1. the target can't have no piece
        // 2. the owner of the piece can't be the same as the executor
        if (target.piece() != null && !target.piece().player().equals(player)) {
            // delete piece first
            // because if not, piece will miss
            target.piece().modifyPositionAs(null);
            target.modifyPieceAs(null);
            return true;
        }
        return false;
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