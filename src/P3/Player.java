package P3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    private final String playerName;
    private final boolean First;
    private final Game game;
    private final Set<Piece> pieces;
    private final List<Action> actions = new ArrayList<>();

    /**
     * initialize a Player and set his pieces
     * @param game
     * @param playerName
     * @param firstFlag
     * @param pieces
     */
    Player(Game game, String playerName, boolean firstFlag, Set<Piece> pieces) {
        this.game = game;
        this.playerName = playerName;
        this.First = firstFlag;
        this.pieces = pieces;
        checkRep();
    }

    /**
     * Rep:
     * playerName can't be blank
     * game can't be null
     * there is no the same piece in pieces
     */
    private void checkRep() {
        assert (playerName != null);
        assert (game != null);
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

    private final Set<String> actionTypes = new HashSet<>() {
        {
            add("put");
            add("move");
            add("capture");
        }
    };

    /**
     * generate a new action and init the action type
     * @param actionType
     * @return
     */
    public Action doAction(String actionType, Piece piece, Position... positions) {
        if (!actionTypes.contains(actionType))
            return null;
        Action action = Action.newAction(this.game.gameType(), this, actionType, piece, positions);
        actions.add(action);
        return action;
    }

    /**
     * @return the number of pieces
     */
    public int sumPiece() {
        return pieces.size();
    }

    /**
     * @return the player's name
     */
    public String name() {
        return this.playerName;
    }
}