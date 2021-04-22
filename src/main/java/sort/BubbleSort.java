package sort;

/**
 * @author : wangqingsong
 * @since : 2020-07-29 16:38:37
 */
public class BubbleSort extends BaseSort {

    @Override
    String getName() {
        return "冒泡排序";
    }

    @Override
    void sortKernel(Comparable[] array) {
        int l = array.length;
        boolean flag = true;
        for (int i = 0; i < l && flag; i++) {
            flag = false;
            for (int j = l - 1; j > i; j--) {
                if (less(array[j], array[j - 1])) {
                    change(array, j, j - 1);
                    flag = true;
                }
            }
        }
    }
}
