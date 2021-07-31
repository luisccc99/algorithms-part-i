
public final class Board {

    public final int[][] tiles;
    private final int n;
    /*
     * create a board from an n-by-n array of tiles,
     * where tiles[row][col] = tile at (row, col)
     */
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] row : tiles) {
            for (int n : row) {
                builder.append(n).append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int outOfPlace = 0;
        int index = 1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int n = tiles[row][col];
                if (n == 0) {
                    index++;
                    continue;
                }
                outOfPlace += n != index ? 1 : 0;
                index++;
            }
        }
        return outOfPlace;
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
        if (this == y) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }

        if (dimension() != ((Board) y).dimension()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++ ) {
                if (this.tiles[i][j] != ((Board) y).tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
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

    public static void main(String[] args) {
        int[][] sample = new int[][] {
                {5, 1, 2},
                {0, 4, 3},
                {8, 7, 6}
        };
        Board b = new Board(sample);
        System.out.println("hamming: " + b.hamming());
        System.out.println(b);
    }

}
