import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private boolean isTwinSolvable;
    private Stack<Board> path;
    private int moves;

    private class SearchNode implements Comparable<SearchNode> {
        int manhattan;
        int moves;
        Board board;
        SearchNode prev;

        public SearchNode(Board board, SearchNode prev,
                          int hamming, int moves) {
            this.board = board;
            this.manhattan = hamming;
            this.moves = moves;
            this.prev = prev;
        }

        // hamming priority
        public int manhattan() {
            return moves + manhattan;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.manhattan() - o.manhattan();
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        Board twin = initial.twin();
        MinPQ<SearchNode> originalTree = new MinPQ<>(initial.hamming());

        MinPQ<SearchNode> twinTree = new MinPQ<>(twin.manhattan());

        SearchNode current = new SearchNode(
                initial,
                null,
                initial.manhattan(),
                0
        );

        SearchNode currentTwin = new SearchNode(
                twin,
                null,
                twin.manhattan(),
                0
        );

        while (!isTwinSolvable) {
            if (currentTwin.board.isGoal()) {
                isTwinSolvable = true;
                break;
            }
            if (current.board.isGoal()) {
                path = new Stack<>();
                moves = current.moves;
                for (SearchNode x = current; x != null; x = x.prev) {
                    path.push(x.board);
                }
                isTwinSolvable = false;
                break;
            }
            current = getNeighborWithMinPriority(current, originalTree);
            currentTwin = getNeighborWithMinPriority(currentTwin, twinTree);

        }
    }

    private SearchNode getNeighborWithMinPriority(SearchNode current,
                                            MinPQ<SearchNode> tree) {
        for (Board neighbor : current.board.neighbors()) {
            if (neighbor.equals(current.board)) {
                continue;
            }
            SearchNode node = new SearchNode(
                    neighbor,
                    current,
                    neighbor.hamming(),
                    current.moves + 1
            );
            tree.insert(node);
        }
        return tree.delMin();
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return !isTwinSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        return path;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }


}
