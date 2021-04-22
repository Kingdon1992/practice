package sort;

/**
 * 算法四
 *
 * @author : wangqingsong
 * @since : 2020-09-09 09:37:11
 */
public class QuickSort extends BaseSort {

    @Override
    String getName() {
        return "快速排序《算法第四版》";
    }

    @Override
    void sortKernel(Comparable[] array) {
        recursion(array, 0, array.length - 1);
    }

    private void recursion(Comparable[] array, int lo, int hi) {
        int pivot;
        while (lo < hi) {
            pivot = partition(array, lo, hi);
            recursion(array, lo, pivot - 1);
            lo = pivot + 1;
        }
    }

    private int partition(Comparable[] array, int lo, int hi) {
        Comparable v = array[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(array[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, array[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            change(array, i, j);
        }
        change(array, lo, j);
        return j;
    }
}
