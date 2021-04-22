package sort;

/**
 * @author : wangqingsong
 * @since : 2020-09-04 17:35:47
 */
public class ShellSort extends BaseSort {

    @Override
    String getName() {
        return "希尔排序";
    }

    @Override
    void sortKernel(Comparable[] array) {
        int h = 1;
        while (h < array.length / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && less(array[j], array[j - h]); j = j - h) {
                    change(array, j, j - h);
                }
            }
            h /= 3;
        }
    }
}
