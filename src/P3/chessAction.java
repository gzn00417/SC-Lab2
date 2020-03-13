package P3;

public class chessAction implements Action {
    private final String actionType;

    /**
     * an Action in chess game
     * @param actionType
     */
    chessAction(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public boolean move(Position... positions) {
        Position source = positions[0], target = positions[1];
        return true;
    }

    @Override
    public boolean capture(Position... positions) {
        Position source = positions[0], target = positions[1];
        return true;
    }

    @Override
    public String actionType() {
        return this.actionType;
    }

}