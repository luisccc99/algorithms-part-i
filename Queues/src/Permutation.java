import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<String> permutation = new RandomizedQueue<>();
        while (StdIn.hasNextLine()) {
            permutation.enqueue(StdIn.readString());
        }
        for (int i = 0; i < n; i++) {
            permutation.dequeue();
        }
    }
}
