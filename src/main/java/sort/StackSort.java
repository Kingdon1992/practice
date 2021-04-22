package sort;

/**
 * @author : wangqingsong
 * @since : 2020-09-12 17:41:41
 */
public class StackSort extends BaseSort {

    @Override
    String getName() {
        return "堆排序";
    }

    @Override
    void sortKernel(Comparable[] array) {
        MaxPriorityQueue maxPriorityQueue = new MaxPriorityQueue(array);
        for (int i = array.length - 1; i > -1; i--) {
            maxPriorityQueue.setValue(i, maxPriorityQueue.deleteMax());
        }
    }

    class MaxPriorityQueue {
        Comparable[] queue;
        int size;

        MaxPriorityQueue(Comparable[] array) {
            queue = array;
            size = array.length;
            for (int i = (array.length - 2) / 2; i > -1; i--) {
                sink(i);
            }
        }

        private void swim(int index) {
            while (index > 0) {
                int i = (index - 1) / 2;
                if (less(queue[i], queue[index])) {
                    change(queue, i, index);
                }
                index = i;
            }
        }

        private void sink(int index) {
            while (index * 2 < size - 1) {
                int i = index * 2 + 1;
                boolean rightFlag = i + 1 < size && less(queue[i], queue[i + 1]);
                if (rightFlag) {
                    //当该节点的右子节点存在，且比左子节点大的时候，使用右子节点比较
                    i++;
                }
                if (less(queue[index], queue[i])) {
                    change(queue, index, i);
                }
                index = i;
            }
        }

        private void setValue(int index, Comparable value) {
            queue[index] = value;
        }

        private Comparable deleteMax() {
            if (size == 0) {
                return null;
            }
            if (size == 1) {
                Comparable max = queue[0];
                queue[0] = null;
                size--;
                return max;
            }
            Comparable max = queue[0];
            queue[0] = queue[size - 1];
            queue[size - 1] = null;
            size--;
            sink(0);
            return max;
        }
    }
}
