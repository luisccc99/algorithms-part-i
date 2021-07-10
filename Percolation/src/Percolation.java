import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public final class Percolation {

    private final boolean[][] grid;
    private int numberOfOpenSites;
    private final int n;
    private final int size;
    private final static int OFFSET = 2;
    private final WeightedQuickUnionUF unionFind;
    private final int TOP_SITE;
    private final int BOTTOM_SITE;

    /**
     * creates n-by-n grid, with all sites initially blocked
     *
     * @param n square of the size
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        size = n * n;
        TOP_SITE = size;
        BOTTOM_SITE = size + 1;
        grid = new boolean[n][n];
        unionFind = new WeightedQuickUnionUF(size + 2);

        // connect first and last row to virtual sites
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                int p;
                if (i == 0) {
                    p = xyTo1D(i, j);
                    unionFind.union(TOP_SITE, p);
                }
                if (i == 1) {
                    p = xyTo1D(n - 1, j);
                    unionFind.union(BOTTOM_SITE, p);
                }
            }
        }
    }

    /**
     * opens the site (row, col) if it is not open already
     *
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }

        grid[row - 1][col - 1] = true;
        numberOfOpenSites++;
        int p = xyTo1D(row - 1, col - 1);
        // check if there are open neighbors to connect to
        for (int i = row - OFFSET; i <= row; i++) {
            for (int j = col - OFFSET; j <= col; j++) {
                boolean corner = isCorner(i, j, row - 1, col - 1);
                boolean isCenter = i == row - 1 && j == col - 1;
                boolean indexInBounds = i >= 0 && j >= 0 && i < n && j < n;
                // adjacent cells only
                if (indexInBounds && !isCenter && !corner) {
                    int q = xyTo1D(i, j);
                    if (isOpen(i + 1, j + 1)) {
                        unionFind.union(p, q);
                    }
                }
            }
        }
    }

    private boolean isCorner(int i, int j, int row, int col) {
        return i == row + 1 && j == col - 1
                || i == row - 1 && j == col + 1
                || i == row + 1 && j == col + 1
                || i == row - 1 && j == col - 1;
    }

    private int xyTo1D(int row, int col) {
        return row * n + col;
    }

    /**
     *
     * @param row
     * @param col
     * @return is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    /**
     *
     * @param row
     * @param col
     * @return is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        int p = xyTo1D(row, col);
        return unionFind.find(p) == unionFind.find(TOP_SITE);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return unionFind.find(TOP_SITE) == unionFind.find(BOTTOM_SITE);
    }

    private void validate(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException();
        }
    }
}