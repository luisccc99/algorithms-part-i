import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver {
    private boolean isSolvable;

    private class SearchNode {
        int hamming;
        int moves;
        Board board;
        SearchNode prev;

        public SearchNode(Board board, SearchNode prev,
                          int hamming, int moves) {
            this.board = board;
            this.hamming = hamming;
            this.moves = moves;
            this.prev = prev;
        }

        // hamming priority
        public int hamming() {
            return moves + hamming;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        Board twin = initial.twin();
        MinPQ<SearchNode> originalTree = new MinPQ<>(initial.hamming(),
                Comparator.comparingInt(SearchNode::hamming));

        MinPQ<SearchNode> twinTree = new MinPQ<>(twin.manhattan(),
                Comparator.comparingInt(SearchNode::hamming));

        SearchNode current = new SearchNode(
                initial, null, initial.hamming(), 0
        );

        SearchNode currentTwin = new SearchNode(
                twin, null, twin.hamming(), 0
        );

        originalTree.insert(current);
        twinTree.insert(currentTwin);

        while (!isSolvable) {
            for (Board neighbor : current.board.neighbors()) {
                if (neighbor.equals(current.board)) {
                    continue;
                }
                System.out.println("adding" + "\n" + neighbor);
                SearchNode node = new SearchNode(
                        neighbor
                        , current
                        , neighbor.hamming()
                        , current.moves + 1
                );
                originalTree.insert(node);
            }
            current = originalTree.delMin();
            if (current.board.isGoal()) {
                isSolvable = true;
                System.out.println("solved");
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return 0;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        return () -> null;
    }

    public static void main(String[] args) {
        // create initial board from file
        /*In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();*/
        int[][] tiles = new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };
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
