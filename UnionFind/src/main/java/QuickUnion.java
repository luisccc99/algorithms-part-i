/** Quick Union is too expensive
 * Trees can get too tall,
 * TODO: finish descriptions
 */
public class QuickUnion extends UnionFind {

    public QuickUnion(int n) {
        super(n);
    }

    /**
     * @param i element to start with
     * @return the root of i
     */
    protected int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    /**
     * Checks whether p and q have the same root
     * if they do it means the are in the same tree
     * @param p
     * @param q
     * @return if p and q are have the same root
     */
    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    @Override
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
