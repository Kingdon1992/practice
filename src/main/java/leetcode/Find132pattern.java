package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : wangqingsong
 * @since : 2021-03-24 10:39:49
 */
public class Find132pattern {
    public static void main(String[] args) {
        System.out.println(find132patternB(new int[]{1, 2, 3, 4}));//false
        System.out.println(find132patternB(new int[]{3, 1, 4, 2}));//ture
        System.out.println(find132patternB(new int[]{-1, 3, 2, 0}));//true
        System.out.println(find132patternB(new int[]{1, 0, 1, -4, -3}));//false
        System.out.println(find132patternB(new int[]{3, 5, 0, 3, 4}));//true
        System.out.println(find132patternB(new int[]{-2, 1, 1, -2, 1, 1}));//false
        System.out.println(find132patternB(new int[]{8, 10, 4, 6, 5}));//true
    }

    public static boolean find132patternB(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return false;
        }
        Deque<Integer> stack = new LinkedList<>();
        stack.push(nums[len-1]);
        int mark=Integer.MIN_VALUE;
        for (int i = len - 2; i >= 0; i--) {
            if(nums[i]<mark){
                return true;
            }
            while(!stack.isEmpty()&&stack.peek()<nums[i]){
                mark=stack.pop();
            }
            if(nums[i]>mark){
                stack.push(nums[i]);
            }
        }
        return false;
    }

    public static boolean find132pattern(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return false;
        }
        int[] hi = new int[len];
        Arrays.fill(hi, len);
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                hi[i] = i + 1;
            } else {
                int now = i + 1;
                while (hi[now] != len) {
                    now = hi[now];
                    if (nums[now] > nums[i]) {
                        hi[i] = now;
                        break;
                    }
                }
            }
        }
        int leftMinIndex = 0;
        for (int i = 1; i < len - 1; i++) {
            int min = nums[leftMinIndex];
            int max = nums[i];
            if (min < max) {
                for (int j = i + 1; j < len; ) {
                    if (nums[j] > min) {
                        if (nums[j] < max) {
                            return true;
                        } else {
                            j++;
                        }
                    } else {
                        j = hi[j];
                    }
                }
            }
            if (nums[i] < nums[leftMinIndex]) {
                leftMinIndex = i;
            }
        }
        return false;
    }
}
