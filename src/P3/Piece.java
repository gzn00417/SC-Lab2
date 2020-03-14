package P3;

public class Piece {
    private final String name;
    private final boolean firstFlag;
    private Position position = null;
    private final Player player;

    /**
     * @param pieceName
     * @param firstFlag
     * @param player
     */
    Piece(String pieceName, boolean firstFlag, Player player) {
        this.name = pieceName;
        this.firstFlag = firstFlag;
        this.player = player;
        checkRep();
    }

    /**
     * name can't be ""
     * player can't be null
     */
    private void checkRep() {
        assert (name != "");
        assert (player != null);
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
     * @return the player who the piece belongs to
     */
    public Player player() {
        return this.player;
    }

    /**
     * @return true is the piece belongs to first player, false if not
     */
    public boolean isFirst() {
        return this.firstFlag;
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