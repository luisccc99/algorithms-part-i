package elementary_sort;

@SuppressWarnings("rawtypes")
public abstract class SortUtils {

    protected static boolean less(Comparable<Comparable> v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    @SuppressWarnings("unchecked assignments")
    protected static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    protected static void swap(Comparable[] arr, int i, int j) {
        Comparable swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
}
