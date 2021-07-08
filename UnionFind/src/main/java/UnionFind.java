public abstract class UnionFind {

    protected int[] id;
    protected int n;

    /**
     * The constructor creates the array and
     * sets the id of each object to itself
     */
    public UnionFind(int n){
        this.n = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public abstract boolean connected(int p, int q);

    public abstract void union(int p, int q);
}
