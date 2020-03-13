package P3;

public class Piece {
    private final String name;
    private Position position;

    /**
     * @param pieceName
     */
    Piece(String pieceName) {
        this.name = pieceName;
    }

    /**
     * @return the name of the Piece
     */
    public String name() {
        return this.name;
    }

    /**
     * @return the Position of the Piece
     */
    public Position position() {
        return this.position;
    }

    /**
     * to update the position of the piece
     * @param newPosition
     * @return true if the position updated successfully, false if the newPosition is null
     */
    public boolean modifyPositionAs(Position newPosition) {
        if (newPosition == null)
            return false;
        this.position = newPosition;
        return true;
    }
}