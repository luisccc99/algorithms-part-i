public class QuickFind {

    private final int[] id;

    /**
     * The constructor sets the id of each object to itself
     */
    public QuickFind(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    /**
     * This method checks whether p and q are in the same component
     * {0}, {1, 2, 3}, {4, 5, 6}
     * 1, 2, and 3 are in the same component so
     * areConnected(1, 2) would return true
     */
    public boolean areConnected(int p, int q) {
        return id[p] == id[q];
    }

}
