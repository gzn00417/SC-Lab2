package P3;

public abstract class MyChessAndGoGame {
    public static Game game;

    public static void main(String[] args) {
        String gameType = "";
        chooseType(gameType);
        generatePlayers();
        GAME();
        printRecord();
    }

    /**
     * provide 2 choices on screen for users to choose chess or go.
     * generate new Game;
     * generate new Board;
     * @param gameType
     */
    public static void chooseType(String gameType) {
        game = Game.newGame(gameType);
    }

    /**
     * get 2 players' names printed on the screen.
     * generate 2 new Player;
     * generate new Piece belonged to Player;
     */
    private static void generatePlayers() {

    }

    /**
     * provide 6 choices including;
     * 1. to put or move a piece : move();
     * 2. to capture particular piece(s) : capture();
     * 3. ask whether a position is free or not : isFree();
     * 4. calculate the sum of the pieces on the board : sumPiece();
     * 5. skip : skip()
     * 6. print "end" : end()
     */
    private static void GAME() {

    }

    /**
     * after the game is ended, to print both players' records of the game.
     */
    private static void printRecord() {

    }
}