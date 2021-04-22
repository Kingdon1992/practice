package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-03-03 15:21:26
 */
public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(new int[]{0, -1, 0, 1, 1});
        System.out.println();
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        //max用于标记已经被二重枚举的数的最大值，小于该值的直接跳过二重枚举
        int max = Integer.MIN_VALUE;
        //因为答案由3个数字组成，假设为abc，那么a元素最多到倒数第三位
        for (int x = 0; x < len - 2; x++) {
            int i = x + 1;
            int j = len - 1;
            if (nums[x] <= max) {
                continue;
            }
            max = nums[x];
            //b、c两个元素不允许重叠,更不允许重复
            int left = Integer.MIN_VALUE;
            int right = Integer.MAX_VALUE;
            while (i < j) {
                //对于第二重循环，重复的元素进行跳过
                if (nums[i] != left && nums[j] != right) {
                    left = nums[i];
                    right = nums[j];
                    int compare = nums[i] + nums[j] + nums[x];
                    if (compare > 0) {
                        //合太大，右指针左移进行减少，左指针保持不动的情况下，还要避免被记为重复
                        j--;
                        left = Integer.MIN_VALUE;
                    } else if (compare < 0) {
                        //合太小，左指针右移进行增大,右指针保持不动的情况下，还要避免被记为重复
                        i++;
                        right = Integer.MAX_VALUE;
                    } else {
                        //发现目标情况
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[x]);
                        result.add(list);
                        //左指针右移的情况下，右指针也必须左移
                        j--;
                        i++;
                    }
                } else {
                    if (left == nums[i]) {
                        i++;
                    }
                    if (right == nums[j]) {
                        j--;
                    }
                }
            }
        }
        return result;
    }
}
