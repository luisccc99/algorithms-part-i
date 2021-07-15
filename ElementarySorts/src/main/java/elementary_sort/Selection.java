package elementary_sort;

import static elementary_sort.SortUtils.*;

/**
 * How does selection sort work
 * O(N^2) even if input is sorted
 */
public class Selection {

    @SuppressWarnings({"rawtypes", "unchecked assignments"})
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[min])) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }
}
