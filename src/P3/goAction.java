package P3;

public class goAction implements Action {
    private final String actionType;

    /**
     * an Action in go game
     * @param actionType
     */
    goAction(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public boolean move(Position... positions) {
        Position target = positions[0];
        return true;
    }

    @Override
    public boolean capture(Position... positions) {
        Position target = positions[0];
        return true;
    }

    @Override
    public String actionType() {
        return this.actionType;
    }

}