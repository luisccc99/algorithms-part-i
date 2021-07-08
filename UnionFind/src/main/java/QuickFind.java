/**
 * Quick find cost model (array accesses) is too expensive.
 *
 * Constructor initialize array which takes N array accesses.
 * Union is too expensive when having to change N entries
 * with a given id (N union commands on N objects would take quadratic time).
 * Find operation is constant.
 */
public class QuickFind extends UnionFind {

    public QuickFind(int n) {
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
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    /**
     * Changes all entries with id[p] to id[q]
     */
    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        if (pid == qid) {
            return;
        }
        for (int i = 0; i < n; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

}
