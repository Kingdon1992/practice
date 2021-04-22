package leetcode;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * @author : wangqingsong
 * @since : 2021-02-21 18:08:39
 */
public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        if (nums.length == 0) {
            return result;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            max = Math.max(nums[i], max);
        }
        result[0] = max;
        for (int i = 1; i + k < nums.length; i++) {
            for (int j = i; j < i + k; j++) {

            }

        }
        return new int[0];
    }
}
