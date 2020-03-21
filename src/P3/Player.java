package P3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    private final String playerName;
    private final boolean First;
    public Game game;
    public Set<Piece> pieces;
    private List<Action> actions = new ArrayList<>(); // the actions record in order

    /**
     * initialize a Player and set his pieces
     * @param game the object of the game that the player is in
     * @param playerName String of the player' name
     * @param firstFlag true if the player is first hand, false if not
     * @param pieces the set of pieces that the player owns
     */
    Player(Game game, String playerName, boolean firstFlag) {
        this.game = game;
        this.playerName = playerName;
        this.First = firstFlag;
        checkRep();
    }

    /**
     * Rep:
     * playerName can't be blank
     * game can't be null
     * there is no the same piece in pieces
     */
    private void checkRep() {
        assert (!this.playerName.isEmpty());
        assert (this.game != null);
    }

    /**
     * @return true if the player plays firstly
     */
    public boolean isFirst() {
        return this.First;
    }

    /**
     * @return the game which the player is gaming in
     */
    public Game game() {
        return this.game;
    }

    /**
     * @return the player's all piece
     */
    public Set<Piece> pieces() {
        return this.pieces;
    }

    /**
     * @return the actions which the player has already done
     */
    public List<Action> actions() {
        return this.actions;
    }

    /**
     * the Strings of the type of whole actions
     */
    private final Set<String> actionTypes = new HashSet<String>() {
        private static final long serialVersionUID = 1L;
        {
            add("put");
            add("move");
            add("capture");
        }
    };

    /**
     * generate a new action and init the action type
     * @param actionType String of the type of the action
     * @return the object of the action created
     */
    public Action doAction(String actionType, Piece piece, Position... positions) {
        if (!actionTypes.contains(actionType))
            return null;
        Action action = Action.newAction(this.game.gameType(), this, actionType, piece, positions);
        if (action.askSuccess())
            actions.add(action);
        else
            action = null;
        return action;
    }

    /**
     * @return the number of pieces
     */
    public int sumPiece() {
        int sum = 0;
        for (Piece piece : pieces) {
            // calculate the non-null piece
            if (piece.position() != null) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * @return the player's name
     */
    public String name() {
        return this.playerName;
    }

    /**
     * @return a free piece belonging to the player
     */
    public Piece freePiece() {
        for (Piece piece : this.pieces) {
            // find a random free piece
            if (piece.position() == null)
                return piece;
        }
        return null;
    }

    /**
     * @param pieceName String of the name of the piece
     * @return piece object of the piece name
     */
    public Piece findPieceByName(String pieceName) {
        for (Piece piece : this.pieces) {
            // find the piece whose name is matched with the giving
            if (piece.name().equals(pieceName))
                return piece;
        }
        return null;
    }
}