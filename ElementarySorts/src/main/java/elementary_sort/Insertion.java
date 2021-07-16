package elementary_sort;

import static elementary_sort.SortUtils.less;
import static elementary_sort.SortUtils.swap;

/**
 * If array is almost sorted, Insertion sort takes linear time
 * to sort the array.
 * Worst case is O(N^2)
 * Insertion sort is twice as fast as selection sort
 */
public class Insertion {

    /**
     * TODO: What are the invariants?
     * @param arr to be sorted 'in place'
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1])) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
