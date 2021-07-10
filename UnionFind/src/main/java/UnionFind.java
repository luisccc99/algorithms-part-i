/**
 * UnionFind is an abstract class that defines an API for classes.
 * • Constructor initialize an array (takes N array accesses).
 */
public abstract class UnionFind {

    protected final int[] id;
    protected final int n;

    /**
     * The constructor creates the array and sets the id
     * of each object to itself.
     *
     * @param n specifies the total number of components
     */
    public UnionFind(final int n) {
        this.n = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    /**
     * Check if p and q are in the same component.
     * @param p component
     * @param q component
     * @return Is there a path connecting p and q?
     */
    public abstract boolean connected(final int p, final int q);

    /**
     * Connects to objects.
     *
     * @param p object in different component
     * @param q object to connect to
     */
    public abstract void union(final int p, final int q);

}
