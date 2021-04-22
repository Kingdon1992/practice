package leetcode;

/**
 * 盛水最多的容器
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 * <p>
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * <p>
 * 输入：height = [1,2,1]
 * 输出：2
 *
 * @author : wangqingsong
 * @since : 2020-12-23 09:34:57
 */
public class MaxArea {
    public static void main(String[] args) {
        //49
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        //1
        System.out.println(maxArea(new int[]{1, 1}));
        //16
        System.out.println(maxArea(new int[]{4, 3, 2, 1, 4}));
        //2
        System.out.println(maxArea(new int[]{1, 2, 1}));
        //8
        System.out.println(maxArea(new int[]{1, 0, 0, 0, 0, 0, 0, 2, 2}));
        //9
        System.out.println(maxArea(new int[]{3, 2, 1, 3}));
        //17
        System.out.println(maxArea(new int[]{2, 3, 4, 5, 18, 17, 6}));
        //25
        System.out.println(maxArea(new int[]{10,9,8,7,6,5,4,3,2,1}));
    }

    /**
     * 双指针
     */
    public static int maxArea(int[] height) {
        int l = height.length;
        int head =0;
        int tail = l-1;
        int maxArea=(tail-head)*Math.min(height[tail],height[head]);
        while (head < tail) {
            if (height[head]>height[tail]){
                tail--;
            }else {
                head++;
            }
            int newArea=(tail-head)*Math.min(height[tail],height[head]);
            maxArea=newArea>maxArea?newArea:maxArea;
        }
        return maxArea;
    }

    /**
     * 分治思想
     */
    public static int second(int[] height) {
        int m = 0;
        int n = 1;
        for (int i = n; i < height.length; i++) {
            if (height[i] >= Math.min(height[m], height[n])) {
                n = i;
                int area = 0;
                for (int j = m; j < i; j++) {
                    int newArea = Math.min(height[j], height[n]) * (n - j);
                    if (newArea > area) {
                        area = newArea;
                        m = j;
                    }
                }
            } else {
                for (int j = m; j >= 0; j--) {
                    if (Math.min(height[i], height[j]) * (i - j) > Math.min(height[m], height[n]) * (n - m)) {
                        n = i;
                        m = j;
                    }
                }
            }
        }
        return (n - m) * Math.min(height[n], height[m]);
    }

    /**
     * 暴力算法
     */
    public static int first(int[] height) {
        int max = 0;
        int area;
        for (int i = 0; i < height.length; i++) {
            for (int j = 1; j < height.length; j++) {
                area = Math.min(height[i], height[j]) * (j - i);
                max = Math.max(area, max);
            }
        }
        return max;
    }
}
