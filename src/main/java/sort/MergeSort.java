package sort;

/**
 * @author : wangqingsong
 * @since : 2020-09-07 11:18:38
 */
public class MergeSort extends BaseSort {

    private Comparable[] aux;

    @Override
    String getName() {
        return "归并排序";
    }

    @Override
    void sortKernel(Comparable[] array) {
        aux = new Comparable[array.length];
        mergeSort(array, 0, array.length - 1);
    }

    void mergeSort(Comparable[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(array, low, mid);
        mergeSort(array, mid+1, high);
        merge(array, low, mid, high);
    }

    private void merge(Comparable[] array, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            aux[i] = array[i];
        }
        int leftIndex = low;
        int rightIndex = mid+1;
        int index = low;
        while (index <= high) {
            if (leftIndex > mid) {
                //左边数组已经遍历完毕，直接填充右边的值
                array[index++] = aux[rightIndex++];
            } else if (rightIndex > high) {
                //右边数组已经遍历完毕，直接填充左边的值
                array[index++] = aux[leftIndex++];
            } else if (less(aux[leftIndex], aux[rightIndex])) {
                //左边小
                array[index++] = aux[leftIndex++];
            } else {
                //右边小
                array[index++] = aux[rightIndex++];
            }
        }
    }
}
