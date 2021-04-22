package sort;

/**
 * @author : wangqingsong
 * @since : 2020-09-04 16:10:07
 */
public class InsertSort extends BaseSort {

    @Override
    String getName() {
        return "插入排序";
    }

    @Override
    void sortKernel(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0 && less(array[j], array[j - 1]); j--) {
                change(array, j, j - 1);
            }
        }
    }
}
