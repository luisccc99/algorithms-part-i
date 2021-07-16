package elementary_sort;

import static elementary_sort.SortUtils.less;
import static elementary_sort.SortUtils.swap;

/**
 * Move entries more than one position at a time
 * by h-sorting the array
 */
public class Shell {

    /**
     *
     * @param arr to be sorted 'in place'
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void sort(Comparable[] arr) {
        int n = arr.length;

        int h = 1;
        while (h < n / 3) {
            h = (3 * h) + 1; // 1, 4, 13, 40, 121, ... e.g. if n = 50, h = 40
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h) {
                    swap(arr, j, j - h);
                }
            }
            h /= 3;
        }
    }
}