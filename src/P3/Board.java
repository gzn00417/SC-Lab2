package P3;

public class Board {
    private final int N;
    private final Game game;
    private Position[][] board;

    /**
     * Initialize a new Board and N*N Position
     * @param boardSide
     */
    Board(Game game, int boardSide) {
        this.game = game;
        this.N = boardSide;
        board = new Position[this.N][this.N];
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                board[i][j] = new Position(i, j);
            }
        }
        checkRep();
    }

    /**
     * Rep:
     * N can't be negative
     * game can't be null
     * board must be full
     */
    private void checkRep() {
        assert (N > 0);
        //assert (game != null);
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                assert (board[i][j] != null);
            }
        }
    }

    /**
     * @param x
     * @param y
     * @return Position of the (x, y)
     */
    public Position positionXY(int x, int y) {
        if (x < 0 || x >= this.N || y < 0 || y >= this.N)
            return null;
        return board[x][y];
    }

    /**
     * @param x
     * @param y
     * @return Piece of the (x, y)
     */
    public Piece pieceXY(int x, int y) {
        if (positionXY(x, y) == null)
            return null;
        return positionXY(x, y).piece();
    }

    /**
     * @param x
     * @param y
     * @return Player if (x, y) is occupied, null if it's free
     */
    public Player XYisFree(int x, int y) {
        if (pieceXY(x, y) == null)
            return null;
        return pieceXY(x, y).player();
    }
}