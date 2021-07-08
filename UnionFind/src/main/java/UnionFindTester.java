import java.util.Scanner;

public class UnionFindTester {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("number of components: ");
        final int entries = scanner.nextInt();
        UnionFind quickFind = new QuickFind(entries);

    }
}
