/**
 * Test client for percolation and percolation stats classes
 */

public class PercolationTester {

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(1,1);
        p.open(2, 1);
        p.open(3, 1);
        p.open(4, 1);
        p.open(5, 1);
        System.out.println(p.percolates());
    }
}
