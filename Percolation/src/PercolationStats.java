import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96d;
    private final double[] thresholds;
    private final int trials;

    /**
     * perform independent trials on an n-by-n grid
     *
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation system = new Percolation(n);
            while (!system.percolates()) {
                int p = StdRandom.uniform(n) + 1;
                int q = StdRandom.uniform(n) + 1;
                system.open(p, q);
            }
            thresholds[i] = (double) system.numberOfOpenSites() / (n * n);
        }

    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(size, trials);
        StdOut.printf("mean\t\t\t\t\t%s%.6f\n", "= ", stats.mean());
        StdOut.printf("stddev\t\t\t\t\t%s%.17f\n", "= ", stats.stddev());
        StdOut.printf("%s confidence interval%s[%.15f], [%.15f]\n", "95%",
                " = ", stats.confidenceHi(), stats.confidenceLo());
    }

}
