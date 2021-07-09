/**
 * Weighted quick union avoids having tall trees
 * by keeping track of the size of each tree.
 */
public class WeightedQuickUnion extends QuickUnion {

    private int[] sz;

    public WeightedQuickUnion(final int n) {
        super(n);
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            sz[i] = 1;
        }
    }

    @Override
    public void union(final int p, final int q) {
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

}
