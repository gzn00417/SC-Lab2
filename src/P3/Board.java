package P3;

public class Board {
    private final int N;
    private final Position[][] board;

    /**
     * Initialize a new Board and N*N Position
     * @param gameSide
     */
    Board(int gameSide) {
        this.N = gameSide;
        board = new Position[gameSide][gameSide];
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                board[i][j] = new Position(i, j);
            }
        }
    }

    /**
     * @param x
     * @param y
     * @return Position of the (x, y)
     */
    public Position positionXY(int x, int y) {
        return board[x][y];
    }

    /**
     * @param x
     * @param y
     * @return Piece of the (x, y)
     */
    public Piece pieceXY(int x, int y) {
        return positionXY(x, y).piece();
    }

    /**
     * @param x
     * @param y
     * @return whether (x, y) is free
     */
    public boolean XYisFree(int x, int y) {
        return pieceXY(x, y) == null;
    }
}