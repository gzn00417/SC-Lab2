package P3;

import java.util.Map;
import java.util.Scanner;

public abstract class MyChessAndGoGame {
    public static Game game;
    public static Player player1, player2;
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        init();
        GAME();
        printRecord();
        input.close();
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
        // scan : 3 String
        System.out.println("Please choose a type of game (chess/go):");
        String gameType = input.nextLine();
        System.out.println("Please write the player1's name (First):");
        String playerName1 = input.nextLine();
        System.out.println("Please write the player2's name (Later):");
        String playerName2 = input.nextLine();
        // new objects
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
        System.out.println("Game Start!");
        while (!endFlag) {
            System.out.println("Please choose a player:");
            String playerName = input.nextLine();
            endFlag = playerActing(game.choosePlayerByName(playerName));
        }
    }

    private static boolean playerActing(Player player) {
        int choice = 0;
        // String[] actionType = new String[] { "put", "move", "capture" };
        System.out.println("Please choose an action type:");
        System.out.println("1. put");
        System.out.println("2. move");
        System.out.println("3. capture");
        System.out.println("4. ask: (x, y) is free?");
        System.out.println("5. ask: sum of both players' pieces");
        System.out.println("6. skip the choose");
        System.out.println("7. end the game");
        String pieceName;
        int x1, y1; // source
        int x2, y2; // target
        while ((choice = input.nextInt()) != 0) {
            switch (choice) {
                case 1: // put
                    pieceName = input.next();
                    x1 = input.nextInt();
                    y1 = input.nextInt();
                    game.put(player, player.findPieceByName(pieceName), game.board().positionXY(x1, y1));
                    return false;
                case 2: // move
                    x1 = input.nextInt();
                    y1 = input.nextInt();
                    x2 = input.nextInt();
                    y2 = input.nextInt();
                    game.move(player, null, game.board().positionXY(x1, y1), game.board().positionXY(x2, y2));
                    return false;
                case 3: // capture
                    if (game.gameType().equals("chess")) {
                        x1 = input.nextInt();
                        y1 = input.nextInt();
                        x2 = input.nextInt();
                        y2 = input.nextInt();
                        game.move(player, null, game.board().positionXY(x1, y1), game.board().positionXY(x2, y2));
                    } else if (game.gameType().equals("go")) {
                        x1 = input.nextInt();
                        y1 = input.nextInt();
                        game.move(player, null, game.board().positionXY(x1, y1));
                    }
                    return false;
                case 4: // is free?
                    x1 = input.nextInt();
                    y1 = input.nextInt();
                    System.out.println(game.isFree(x1, y1));
                    return false;
                case 5: // sum of pieces
                    Map<Player, Integer> sumPiece = game.sumPiece();
                    System.out.println(game.player1().name() + ":" + sumPiece.get(game.player1()));
                    System.out.println(game.player2().name() + ":" + sumPiece.get(game.player2()));
                    return false;
                case 6: // skip
                    game.skip();
                    return false;
                case 7: // end
                    game.end();
                    return true;
            }
            System.out.println("Input WRONG, Please choose again:");
        }
        return false;
    }

    /**
     * after the game is ended, to print both players' records of the game.
     */
    private static void printRecord() {

    }
}