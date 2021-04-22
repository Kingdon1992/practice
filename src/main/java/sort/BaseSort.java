package sort;

import util.StdDraw;

import java.awt.*;
import java.util.Random;

/**
 * @author : wangqingsong
 * @since : 2020-09-04 15:53:23
 */
public abstract class BaseSort {

    private int high = 100;

    private boolean graphFlag = false;

    /**
     * 获取排序名称
     *
     * @return 排序名称
     */
    abstract String getName();

    /**
     * 排序算法的内核
     *
     * @param array 待排序数组
     */
    abstract void sortKernel(Comparable[] array);

    /**
     * 单次排序
     *
     * @param array 待排序数组
     * @return 本次排序所花费的时间
     */
    public long sort(Comparable[] array) throws Exception {
        long begin = System.currentTimeMillis();
        sortKernel(array);
        long end = System.currentTimeMillis();
        if (!isSorted(array)) {
            throw new Exception("排序失败");
        }
        return end - begin;
    }

    /**
     * 批量排序性能测试
     *
     * @param sortCount 排序次数，进行多少次排序
     * @param sortSize  排序规模，数组的规模
     * @throws Exception 排序失败
     */
    public void batchSort(int sortCount, int sortSize) throws Exception {
        Random random = new Random();
        long totalTime = 0;
        for (int i = 0; i < sortCount; i++) {
            Integer[] array = new Integer[sortSize];
            for (int j = 0; j < sortSize; j++) {
                array[j] = random.nextInt(high);
            }
            //创建图
            if (graphFlag) {
                graph(array, high);
            }
            totalTime += sort(array);
        }
        System.out.println(getName() + ":" + totalTime);
    }

    protected boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    protected void change(Comparable[] array, int i, int j) {
        if (graphFlag) {
            changeGraph((Integer[]) array, i, j);
        }

        Comparable mid = array[i];
        array[i] = array[j];
        array[j] = mid;
    }

    public boolean isSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public void graph(Integer[] array, int high) {
        int l = array.length;
        StdDraw.setXscale(-l * 0.1, l * 1.1);
        StdDraw.setYscale(-high * 0.1, high * 1.1);
        StdDraw.line(-l * 0.1, 0, l * 1.1, 0);
        for (int i = 0; i < l; i++) {
            StdDraw.filledRectangle(i, array[i] / 2, 0.4, array[i] / 2);
        }
    }

    private void changeGraph(Integer[] array, int i, int j) {
        Integer[] integers = array;
        StdDraw.setPenColor(Color.white);
        StdDraw.filledRectangle(j, integers[j] / 2, 0.4, integers[j] / 2);
        StdDraw.filledRectangle(i, integers[i] / 2, 0.4, integers[i] / 2);
        StdDraw.setPenColor(Color.pink);
        StdDraw.filledRectangle(i, integers[j] / 2, 0.4, integers[j] / 2);
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(j, integers[i] / 2, 0.4, integers[i] / 2);
    }
}
