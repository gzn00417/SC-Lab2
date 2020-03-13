package P3;

public class Position {
    private final int x, y;
    private Piece piece = null;

    Position(int X, int Y) {
        this.x = X;
        this.y = Y;
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
     * to update the Piece of the Position
     * @param newPiece
     * @return true if the Piece updated successfully, false if the new Piece is null
     */
    public boolean modifyPieceAs(Piece newPiece) {
        if (newPiece == null)
            return false;
        this.piece = newPiece;
        return true;
    }

}