package P3;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class MyChessAndGoGame {
    public static Scanner input = new Scanner(System.in);

    /**
     * provide 2 choices on screen for users to choose chess or go. generate new
     * Game; generate new Board;
     * 
     * get 2 players' names printed on the screen. generate 2 new Player; generate
     * new Piece belonged to Player;
     */
    public static void main(String[] args) {
	// scan : 3 String
	System.out.println("Please choose a type of game (chess/go):");
	String gameType = input.nextLine();
	System.out.println("Please write the player1's name (First):");
	String playerName1 = input.nextLine();
	System.out.println("Please write the player2's name (Later):");
	String playerName2 = input.nextLine();
	// new objects
	final Game game = Game.newGame(gameType);
	final Player player1 = new Player(game, playerName1, true);
	final Player player2 = new Player(game, playerName2, false);
	game.setPlayers(player1, player2);
	player1.pieces = game.pieces(true);
	player2.pieces = game.pieces(false);
	GAME(game);
	printRecord(game, player1, player2);
	input.close();
    }

    /**
     * provide 7 choices including; 
     * 1. to put a piece : put(); 
     * 2. to move a piece : move(); 
     * 3. to capture particular piece(s) : capture(); 
     * 4. ask whether a position is free or not : isFree(); 
     * 5. calculate the sum of the pieces on the
     * board : sumPiece(); 
     * 6. skip : skip() 
     * 7. print "end" : end()
     */
    private static void GAME(Game game) {
	boolean endFlag = false;
	System.out.println("Game Start!");
	while (!endFlag) {
	    System.out.println("Please choose a player:");
	    String playerName = input.next();
	    endFlag = playerActing(game, game.choosePlayerByName(playerName));
	    game.board().printBoard();
	}
    }

    private static boolean playerActing(Game game, Player player) {
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
	int choice = input.nextInt();
	while (choice != 0) {
	    switch (choice) {
	    case 1: // put
		pieceName = input.next();
		x1 = input.nextInt();
		y1 = input.nextInt();
		System.out
			.println(game.put(player, player.findPieceByName(pieceName), game.board().positionXY(x1, y1)));
		return false;
	    case 2: // move
		x1 = input.nextInt();
		y1 = input.nextInt();
		x2 = input.nextInt();
		y2 = input.nextInt();
		System.out.println(game.move(player, game.board().positionXY(x1, y1), game.board().positionXY(x2, y2)));
		return false;
	    case 3: // capture
		if (game.gameType().equals("chess")) {
		    x1 = input.nextInt();
		    y1 = input.nextInt();
		    x2 = input.nextInt();
		    y2 = input.nextInt();
		    System.out.println(
			    game.move(player, game.board().positionXY(x1, y1), game.board().positionXY(x2, y2)));
		} else if (game.gameType().equals("go")) {
		    x1 = input.nextInt();
		    y1 = input.nextInt();
		    System.out.println(game.move(player, game.board().positionXY(x1, y1)));
		}
		return false;
	    case 4: // is free?
		x1 = input.nextInt();
		y1 = input.nextInt();
		Player here = game.isFree(x1, y1);
		System.out.println(here == null ? "Free" : here.name());
		return false;
	    case 5: // sum of pieces
		Map<Player, Integer> sumPiece = game.sumPiece();
		System.out.println(game.player1().name() + ":" + sumPiece.get(game.player1()));
		System.out.println(game.player2().name() + ":" + sumPiece.get(game.player2()));
		return false;
	    case 6: // skip
		game.skip();
		System.out.println("skip");
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
    private static void printRecord(Game game, Player player1, Player player2) {
	List<Action> actions1 = player1.actions();
	List<Action> actions2 = player2.actions();
	System.out.println("\n" + player1.name() + "'s Actions:");
	for (int i = 0; i < actions1.size(); i++) {
	    if (actions1.get(i) != null)
		System.out.println(i + ": " + actions1.get(i).actionType());
	}
	System.out.println("\n" + player2.name() + "'s Actions:");
	for (int i = 0; i < actions2.size(); i++) {
	    if (actions2.get(i) != null)
		System.out.println(i + ": " + actions2.get(i).actionType());
	}
    }
}