package sort;

/**
 * 大话数据结构
 *
 * @author : wangqingsong
 * @since : 2020-09-09 09:37:11
 */
public class QuickSort2 extends BaseSort {

    @Override
    String getName() {
        return "快速排序《大话数据结构》";
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
        while (i < j) {
            while (i < j && less(v, array[--j])) {
            }
            array[i] = array[j];
            while (i < j && less(array[++i], v)) {
            }
            array[j] = array[i];
        }
        array[i] = v;
        return i;
    }
}
