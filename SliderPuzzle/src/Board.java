import java.util.Arrays;

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
        builder.append(n).append('\n');
        for (int[] row : tiles) {
            for (int n : row) {
                builder.append(String.format("%2d", n));
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
        int total = 0;
        int index = 1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int n = tiles[row][col];
                if (n == 0) {
                    index++;
                    continue;
                }
                total += n != index ? 1 : 0;
                index++;
            }
        }
        return total;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int total = 0;
        int index = 1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int n = tiles[row][col];
                if (n == 0) {
                    index++;
                    continue;
                }
                if (n != index) {
                    int diff = Math.abs(n - index);
                    if (diff > 2) {
                        diff /= 2;
                    }
                    total += diff;
                }
                index++;
            }
        }
        return total;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int index = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    index++;
                    continue;
                }
                if (tiles[i][j] != index) {
                    return false;
                }
                index++;
            }
        }
        return true;
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
        return Arrays.deepEquals(this.tiles, ((Board) y).tiles);
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
        int[][] sample = new int[][]{
                {4, 1, 3},
                {0, 2, 5},
                {7, 8, 6}
        };
        Board b = new Board(sample);
        System.out.println("hamming: " + b.hamming());
        System.out.println("manhattan: " + b.manhattan());
        System.out.println(b);
    }

}
