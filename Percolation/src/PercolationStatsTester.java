import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStatsTester {

    public static void main(String[] args) {
        int n = Integer.parseInt(StdIn.readString());
        int trials = Integer.parseInt(StdIn.readString());
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.printf("mean\t\t\t\t\t%s%.6f\n", "= ", stats.mean());
        StdOut.printf("stddev\t\t\t\t\t%s%.17f\n", "= ", stats.stddev());
        StdOut.printf("%s confidence interval%s[%.15f], [%.15f]\n", "95%"
                ," = ", stats.confidenceHi(), stats.confidenceLo());
    }
}
