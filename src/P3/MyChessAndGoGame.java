package P3;

import java.util.ArrayList;
import java.util.List;

public abstract class MyChessAndGoGame {
    public static Game game;
    public static Player player1, player2;

    public static void main(String[] args) {
        init();
        GAME();
        printRecord();
    }

    /**
     * provide 2 choices on screen for users to choose chess or go.
     * generate new Game;
     * generate new Board;
     * 
     * get 2 players' names printed on the screen.
     * generate 2 new Player;
     * generate new Piece belonged to Player;
     */
    private static void init() {
        String gameType = "", playerName1 = "", playerName2 = "";

        // scan : 3 String

        game = Game.newGame(gameType);
        player1 = new Player(game, playerName1, true, game.pieces(true));
        player2 = new Player(game, playerName2, false, game.pieces(false));
        game.setPlayers(player1, player2);
    }

    /**
     * provide 7 choices including;
     * 1. to put a piece : put();
     * 2. to move a piece : move();
     * 3. to capture particular piece(s) : capture();
     * 4. ask whether a position is free or not : isFree();
     * 5. calculate the sum of the pieces on the board : sumPiece();
     * 6. skip : skip()
     * 7. print "end" : end()
     */
    private static void GAME() {
        boolean endFlag = false;
        while (!endFlag) {
            endFlag = playerActing(player1);
            if (endFlag == false)
                endFlag = playerActing(player2);
        }
    }

    private static boolean playerActing(Player player) {
        int choice = 0;
        String[] actionType = new String[] { "put", "move", "capture" };
        switch (choice) {
            case 1:
                // scan : piece position
                game.put(player, piece, position);
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return true;
        }
        return false;
    }

    /**
     * after the game is ended, to print both players' records of the game.
     */
    private static void printRecord() {

    }
}