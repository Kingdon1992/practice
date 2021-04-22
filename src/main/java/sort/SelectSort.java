package sort;

/**
 * @author : wangqingsong
 * @since : 2020-09-04 15:54:39
 */
public class SelectSort extends BaseSort {

    @Override
    String getName() {
        return "选择排序";
    }

    @Override
    public void sortKernel(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array[j], array[min])) {
                    min = j;
                }
            }
            change(array, i, min);
        }
    }
}