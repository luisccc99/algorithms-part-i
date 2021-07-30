
public class Board {

    private int[][] tiles;

    /*
     * create a board from an n-by-n array of tiles,
     * where tiles[row][col] = tile at (row, col)
     */
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    @Override
    public String toString() {
        return null;
    }

    // board dimension n
    public int dimension() {
        return 0;
    }

    // number of tiles out of place
    public int hamming() {
        return 0;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return () -> null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

}
