import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        String champion = StdIn.readString();
        int i = 1;
        while (!StdIn.isEmpty()) {
            String current = StdIn.readString();
            double p = 1.0 / ++i;
            boolean newChampion = StdRandom.bernoulli(p);
            if (newChampion) {
                champion = current;
            }
        }
        StdOut.println(champion);
    }
}
