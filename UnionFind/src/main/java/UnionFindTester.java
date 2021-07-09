import java.util.Scanner;

class UnionFindTester {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("number of components: ");
        final int entries = scanner.nextInt();
        UnionFind quickFind = new QuickFind(entries);
        int p;
        int q;
        while (!scanner.hasNext()) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            if (!quickFind.connected(p, q)) {
                quickFind.union(p, q);
                System.out.println("Connecting " + p + " and " + q);
            }
        }
    }
}
