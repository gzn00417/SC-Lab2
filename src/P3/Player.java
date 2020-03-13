package P3;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final boolean First;
    private final Set<Piece> pieces = new HashSet<>();

    Player(boolean firstFlag) {
        this.First = firstFlag;
    }
}