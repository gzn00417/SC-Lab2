package P3;

public class Position {
    private final int x, y;
    private Piece piece = null;

    Position(int X, int Y) {
        this.x = X;
        this.y = Y;
        checkRep();
    }

    /**
     * x, y must be non-negative
     */
    private void checkRep() {
        assert (x >= 0 && y >= 0);
    }

    /**
     * @return x of the Position
     */
    public int x() {
        return this.x;
    }

    /**
     * @return y of the Position
     */
    public int y() {
        return this.y;
    }

    /**
     * @return the Piece in this Position
     */
    public Piece piece() {
        return this.piece;
    }

    /**
     * @return player if the position is occupied, null if it's free
     */
    public Player player() {
        if (this.piece == null)
            return null;
        return this.piece.player();
    }

    /**
     * to update the Piece of the Position
     * @param newPiece
     * @return true if the Piece updated successfully, false if the new Piece is null
     */
    public boolean modifyPieceAs(Piece newPiece) {
        if (newPiece == null)
            return false;
        this.piece = newPiece;
        checkRep();
        return true;
    }

}