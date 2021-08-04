import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;
import java.util.Objects;

public final class Board {

    private final int[][] tiles;
    private final int n;
    private int blankRow;
    private int blankCol;

    /*
     * create a board from an n-by-n array of tiles,
     * where tiles[row][col] = tile at (row, col)
     */
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
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
        int result = Objects.hash(n);
        result = 31 * result + Arrays.deepHashCode(tiles);
        return result;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<>();
        Board neighbor;
        // north
        if (blankRow - 1 >= 0) {
            neighbor = new Board(swapBlankTile(blankRow - 1, blankCol));
            neighbors.enqueue(neighbor);
        }
        //south
        if (blankRow + 1 <= n - 1) {
            neighbor = new Board(swapBlankTile(blankRow + 1, blankCol));
            neighbors.enqueue(neighbor);
        }
        //west
        if (blankCol - 1 >= 0) {
            neighbor = new Board(swapBlankTile(blankRow, blankCol - 1));
            neighbors.enqueue(neighbor);
        }
        // east
        if (blankCol + 1 <= n - 1) {
            neighbor = new Board(swapBlankTile(blankRow, blankCol + 1));
            neighbors.enqueue(neighbor);
        }
        return neighbors;
    }

    private int[][] swapBlankTile(int row, int col) {
        int[][] cp = copyOfTiles();
        int tile = cp[row][col];
        cp[row][col] = 0;
        cp[blankRow][blankCol] = tile;
        return cp;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int row = blankRow + 1 <= n - 1 ? blankRow + 1 : blankRow - 1;
        int[][] cp = copyOfTiles();
        int tile = cp[row][0];
        cp[row][0] = cp[row][1];
        cp[row][1] = tile;
        return new Board(cp);
    }

    private int[][] copyOfTiles() {
        int[][] cp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cp[i][j] = tiles[i][j];
            }
        }
        return cp;
    }

    public static void main(String[] args) {
        int[][] sample = new int[][]{
                {1, 5, 3},
                {2, 0, 6},
                {7, 4, 8}
        };
        Board b = new Board(sample);
        System.out.println("hamming: " + b.hamming());
        System.out.println("manhattan: " + b.manhattan());
        System.out.println("is goal: " + b.isGoal());
        System.out.println(b);
        System.out.println("neighbors");
        Iterable<Board> iterable = b.neighbors();
        for (Board board : iterable) {
            System.out.println(board);
        }
        System.out.println("twin");
        System.out.println(b.twin());
    }
}
