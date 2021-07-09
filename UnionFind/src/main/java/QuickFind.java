/**
 * Quick find cost model (array accesses) is too expensive.
 * • Union is too expensive N union commands on N objects would take quadratic time.
 * • Find operation is constant.
 */
public class QuickFind extends UnionFind {

    public QuickFind(final int n) {
        super(n);
    }

    /**
     * This method checks whether p and q are in the same
     * connected component, for instance:
     * {0}, {1, 2, 3}, {4, 5, 6}
     * 1, 2, and 3 are in the same component so
     * areConnected(1, 2) would return true
     */
    @Override
    public boolean connected(final int p, final int q) {
        return id[p] == id[q];
    }

    /**
     * Changes all entries with id[p] to id[q]
     */
    @Override
    public void union(final int p, final int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < n; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

}
