import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<String> permutation = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            permutation.enqueue(StdIn.readString());
        }
        for (int i = 0; i < n; i++) {
            StdOut.println(permutation.dequeue());
        }
    }
}
